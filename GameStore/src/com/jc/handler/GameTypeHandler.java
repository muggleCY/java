package com.jc.handler;

import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.jc.pojo.GameType;

public class GameTypeHandler implements TypeHandler<GameType>{

	@Override
	public GameType getResult(ResultSet rs, String col) throws SQLException {
		GameType gameType = null;
		try {
			Reader reader = Resources.getResourceAsReader("MyXml.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			gameType = factory.openSession().selectOne("GameTypeMapper.selectGameTypeById",rs.getInt(col));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gameType;
	}

	@Override
	public GameType getResult(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameType getResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, GameType arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
