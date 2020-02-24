package com.web.dao;

import com.web.vo.MenuVO;

import java.util.List;

public interface MenuVODao {
    List<MenuVO> selectCurrentMenuByRoleId(Integer id);
}
