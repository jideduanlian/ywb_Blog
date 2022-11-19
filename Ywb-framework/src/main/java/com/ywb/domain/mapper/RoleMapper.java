package com.ywb.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywb.domain.entity.Menu;
import com.ywb.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 09:17:31
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> SelectRoleByUserId(Long id);

    List<Menu> selectAllMenuList();

    List<Long> selectRoleIdByUserId(Long userId);

    List<String> selectRoleKeyByUserId(Long id);
}

