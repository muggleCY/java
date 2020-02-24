package com.jc.mapper;

import java.sql.ResultSet;

public class CountMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws Exception {
		return rs.getInt("num");
	}

}
