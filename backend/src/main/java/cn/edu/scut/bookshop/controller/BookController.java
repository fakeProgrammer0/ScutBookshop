package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.BookCategory;
import cn.edu.scut.bookshop.service.BookService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation("获取图书类别")
    @GetMapping("/books/categories")
    public Result getBookCatagories(){
        return bookService.getBookCatagories();
    }
    
    @ApiOperation("获取图书类别与类别下的热门图书")
    @GetMapping("/books/categories-books")
    public Result getBookCatagoriesBooks(){
        return bookService.getBookCatagoriesBooks();
    }

    @ApiOperation("分页查询某类别下面的书籍")
    @GetMapping("/books/categories/{category_id}")
    public Result getAllBookInCatagory(@PathVariable int category_id,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int per_page){
        PageHelper.startPage(page,per_page);
        return bookService.getAllBookInCatagory(category_id);
    }


    @ApiOperation("获取图书的短评")
    @GetMapping("/books/{id}/short-comments")
    public Result getShortComments(@PathVariable int id){
        return bookService.getShortComments(id);
    }

    @ApiOperation("获取图书所属的标签")
    @GetMapping("/books/{id}/tags")
    public Result getBookTagRelations(@PathVariable int id){
        return bookService.getBookTagRelations(id);
    }
}
