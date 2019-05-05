package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class BookController
{
    @Autowired
    private BookService bookService;
    
    @ApiOperation("获取图书详情信息")
    @GetMapping("/books/{id}")
    public Result getBookDetail(@PathVariable int id)
    {
        return bookService.getBookDetail(id);
    }
}
