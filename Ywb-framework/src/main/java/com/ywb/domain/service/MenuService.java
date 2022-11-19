package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.Dto.AddMenuDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Menu;
import com.ywb.domain.vo.PageVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-11-15 09:05:23
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPremsByUserId(Long id);

    List<Menu> slelctRouterMenuTreeByUserId(Long id);

//    ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, Menu menu);

    ResponseResult add(AddMenuDto menu);

    ResponseResult getMenu(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenu(Long id);

    List<Menu> listAll();

    List<Menu> selectMenuList(Menu menu);

    List<Long> selectMenuListByRoleId(Long roleId);


}

