package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.entity.RoleMenu;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2022-11-17 13:59:01
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}

