package com.ywb.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywb.domain.Dto.AddMenuDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.constants.SystemConstants;
import com.ywb.domain.entity.Menu;
import com.ywb.domain.mapper.MenuMapper;
import com.ywb.domain.service.MenuService;
import com.ywb.domain.utils.BeanCopyUtils;
import com.ywb.domain.utils.SecurityUtils;
import com.ywb.domain.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.sun.javafx.robot.impl.FXRobotHelper.getChildren;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 09:05:23
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPremsByUserId(Long id) {
        //如果是管理员返回所有权限
        if (id==1L){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> list = list(queryWrapper);
            List<String> collect = list.stream().map(Menu::getPerms).collect(Collectors.toList());
            return  collect;
        }
        //如果不是管理员，返回具有的权限
        return getBaseMapper().selectPremsByUserId(id);

    }

    @Override
    public List<Menu> slelctRouterMenuTreeByUserId(Long id) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if (SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(id);
        }
        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }


    @Override
    public ResponseResult add(AddMenuDto menu) {
        Menu menu1 = BeanCopyUtils.copyBean(menu, Menu.class);
        save(menu1);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenu(Long id) {
        Menu byId = getById(id);
        return ResponseResult.okResult(byId);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long id) {
        Menu byId = getById(id);
        if (!StringUtils.hasText(byId.getParentId().toString())){
            removeById(id);
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(500,"存在子菜单不允许删除");
    }

    @Override
    public List<Menu> listAll() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        List<Menu> menu = list(queryWrapper);
        return menu;
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //menuName模糊查询
        queryWrapper.like(StringUtils.hasText(menu.getMenuName()),Menu::getMenuName,menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()),Menu::getStatus,menu.getStatus());
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);
        return menus;
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuListByRoleId(roleId);
    }


    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream().filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;

    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> collect = menus.stream().filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return collect;
    }
}
