package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-11-08 19:42:12
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult list(Integer pageNum, Integer pageSize, Link link);

    ResponseResult addLink(Link link);

    ResponseResult listById(Integer id);

    ResponseResult updateLink(Link link);

    ResponseResult deleteLink(Integer id);
}

