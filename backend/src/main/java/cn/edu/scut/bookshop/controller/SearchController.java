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
    
    @ApiOperation("S1.搜索图书、作者")
    @GetMapping("/search")
    public Result hybridSearch(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "10") int size)
    {
        return searchService.hybridSearch(keyword, size);
    }
    
    @ApiOperation("S2.搜索图书")
    @GetMapping("/search/books")
    public Result searchBooks(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "10") int size)
    {
        return searchService.searchBooks(keyword, size);
    }
    
    @ApiOperation("S3.搜索作者")
    @GetMapping("/search/authors")
    public Result searchAuthors(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "10") int size)
    {
        return searchService.searchAuthors(keyword, size);
    }
    
    @ApiOperation("S4.搜索ISBN")
    @GetMapping("/search/isbn")
    public Result searchISBN(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "10") int size)
    {
        return searchService.searchISBN(keyword, size);
    }
}
