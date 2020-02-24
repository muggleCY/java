package com.jc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;



public class JDBCUtil {//工具类，主要提供数据库连接，关闭连接
//	每个线程都应该拥有独立的连接，以保证事务和sql执行是在同一个connect对象中
	static ThreadLocal<Connection> myConn = new ThreadLocal<Connection>();
	static DataSource ds = null;
	/**
	 * 静态代码块，读取配置文件，创建DataSource对象
	 */
	static{
		Properties p = new Properties();
		try {
			p.load(JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 使用DataSource获取数据库连接
	 * @return conn 数据库连接对象
	 */
	public static Connection getConnection(){
		Connection conn = null;
//		判断当前线程是否拥有连接
		conn = myConn.get();
//		如果没有就获取一个连接
		if(conn == null){
			try {
				conn = ds.getConnection();
//				并将获取到的连接放入线程的私有空间中，保证当前线程始终操作同一个数据库连接
				myConn.set(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return conn;
	}
	/**
	 * 关闭连接、状态集、结果集
	 * @param conn  数据库连接
	 * @param stmt	状态集
	 * @param rs	结果集
	 */
	public static void close(Statement stmt,ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭当前线程中的连接
	 */
	public static void close(){
		Connection conn = myConn.get();
		if (conn != null) {
			try {
				conn.close();
				myConn.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
