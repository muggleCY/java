package com.jc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.mapper.RowMapper;

public class JDBCTemplate<T> {//模板类，提供增删改查的标准方法
	/**
	 * 执行修改语句标准方法
	 * @param sql  数据库语句
	 * @param params  参数
	 * @return
	 */
	public Integer update(String sql ,Object... params){
		Integer countRow = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			countRow = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt,null);
		}
		return countRow;
	}
	/**
	 * 执行添加语句标准方法
	 * @param sql  数据库语句
	 * @param params  参数
	 * @return
	 */
	public Integer insert(String sql ,Object... params){
		return update(sql, params);
	}
	/**
	 * 执行删除语句标准方法
	 * @param sql  数据库语句
	 * @param params  参数
	 * @return
	 */
	public Integer delete(String sql ,Object... params){
		return update(sql, params);
	}
	/**
	 * 执行查询多行语句标准方法
	 * @param sql  数据库语句
	 * @param params  参数
	 * @return
	 */
	public List<T> selectAll(RowMapper<T> rm,String sql,Object... params){
		List<T> result = new ArrayList<T>();//结果集合       为什么要获取结果集的数组形式？就是为了关闭结果集吗？
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();//查询结果。伪表  行   列
			while (rs.next()) {//逐行读取查询到的数据
//				rs --> object  映射
				result.add(rm.mapperObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, rs);
		}
		return result;
	}
	/**
	 * 执行查询单行语句标准方法
	 * @param sql  数据库语句
	 * @param params  参数
	 * @return
	 */
	public T selectOne(RowMapper<T> rm,String sql,Object... params){
		List<T> list = selectAll(rm, sql, params);
		T obj = null;
		if(list.size() != 0){
			obj = list.get(0);
		}
		return obj;
	}
}
