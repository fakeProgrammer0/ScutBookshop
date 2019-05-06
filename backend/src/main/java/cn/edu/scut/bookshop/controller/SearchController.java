package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class SearchController
{
    @Autowired
    private SearchService searchService;
    
    @ApiOperation("搜索图书、作者")
    @GetMapping("/search")
    public Result search(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "10") int size)
    {
        return searchService.search(keyword, size);
    }
}
