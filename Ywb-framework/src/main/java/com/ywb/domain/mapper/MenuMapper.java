package com.ywb.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywb.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 09:05:22
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPremsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long id);

    List<Long> selectMenuListByRoleId(Long roleId);

}

