package com.Ywb.controller;

import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Link;
import com.ywb.domain.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, Link link){
        return linkService.list(pageNum, pageSize, link);
    }
    @PostMapping
    public ResponseResult addLink(@RequestBody Link link){
        return linkService.addLink(link);
    }
    @GetMapping("/{id}")
    public ResponseResult listById(@PathVariable Integer id){
        return linkService.listById(id);
    }
    @PutMapping
    public  ResponseResult updateLink(@RequestBody Link link){
        return linkService.updateLink(link);
    }
    @DeleteMapping
    public ResponseResult deleteLink(@PathVariable Integer id){
        return  linkService.deleteLink(id);
    }
}
