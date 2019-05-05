package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.service.UserBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class UserBookController
{
    @Autowired
    private UserBookService userBookService;
    
    @ApiOperation("添加图书到收藏夹")
    @PostMapping("/users/{user_id}/collections")
    public Result addCollection(@PathVariable int user_id, @RequestParam int book_id)
    {
        return userBookService.addCollection(user_id,book_id);
    }
    
    @ApiOperation("从收藏夹中删除图书")
    @DeleteMapping("/users/{user_id}/collections")
    public Result removeCollection(@PathVariable int user_id, @RequestParam int book_id)
    {
        return userBookService.removeCollection(user_id, book_id);
    }
    
    @ApiOperation("检查用户是否收藏该图书")
    @GetMapping("/users/{user_id}/collections/{book_id}")
    public Result checkBookCollected(@PathVariable int user_id, @PathVariable int book_id)
    {
        return userBookService.checkBookCollected(user_id, book_id);
    }
    
    @ApiOperation("获取用户收藏图书")
    @GetMapping("/users/{user_id}/collections")
    public Result getCollections(int user_id, int page, int per_page)
    {
        return userBookService.getCollections(user_id, page, per_page);
    }
}
