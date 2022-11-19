package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.Dto.TagListDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Tag;
import com.ywb.domain.vo.PageVo;
import com.ywb.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-11-13 20:18:15
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);


    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);

    List<TagVo> listAllTag();
}

