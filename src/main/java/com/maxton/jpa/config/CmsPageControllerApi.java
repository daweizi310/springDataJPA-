package com.maxton.jpa.config;

import com.maxton.jpa.pojo.Article;
import com.maxton.jpa.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "jpa测试多表查询", description = "jpa测试多表查询")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public Result findSearch(int page, int size, Article article);

    //新增页面
    @ApiOperation("新增")
    public Result add(Article article);

    //根据页面id查询页面信息
    @ApiOperation("根据页面id查询信息")
    public Article findById(String id);

    //修改页面
    @ApiOperation("修改")
    public Result update(String id, Article article);

    //删除页面
    @ApiOperation("删除")
    public Result delete(String id);
}
