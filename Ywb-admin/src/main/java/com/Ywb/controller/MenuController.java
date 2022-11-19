package com.Ywb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ywb.domain.Dto.AddMenuDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Menu;
import com.ywb.domain.service.MenuService;
import com.ywb.domain.utils.BeanCopyUtils;
import com.ywb.domain.utils.SystemConverter;
import com.ywb.domain.vo.MenuTreeVo;
import com.ywb.domain.vo.MenuVo;
import com.ywb.domain.vo.PageVo;
import com.ywb.domain.vo.RoleMenuTreeSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public ResponseResult treeselect() {
        //复用之前的selectMenuList方法。方法需要参数，参数可以用来进行条件查询，而这个方法不需要条件，所以直接new Menu()传入
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<MenuTreeVo> options =  SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(options);
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys,menuTreeVos);
        return ResponseResult.okResult(vo);
    }

    public ResponseResult list(){

        List<Menu> menu= menuService.listAll();
        return ResponseResult.okResult(menu);

    }
    @GetMapping("/list")
    public ResponseResult list1( Menu menu){
        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }
    @PostMapping
    public ResponseResult add(@RequestBody AddMenuDto menu){
        return menuService.add(menu);
    }
    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable("id") Long id){
        return menuService.getMenu(id);
    }
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
    @DeleteMapping("/{Menuid}")
    public ResponseResult deleteMenu(@PathVariable("id") Long id){
        return menuService.deleteMenu(id);
    }

}
