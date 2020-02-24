package com.web.mapper;

import java.sql.ResultSet;

public class MaxNoMapper implements RowMapper<Integer>{

	@Override
	public Integer mapperObject(ResultSet rs) throws Exception {
		return rs.getInt("num");
	}
	
}
