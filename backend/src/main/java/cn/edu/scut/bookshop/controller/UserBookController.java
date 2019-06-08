package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.service.UserBookService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

//@Valid
@Validated
@Api
@RestController
public class UserBookController
{
    @Autowired
    private UserBookService userBookService;
    
    @ApiOperation("UB1.添加图书到收藏夹")
    @PostMapping("/users/{user_id}/collections/{book_id}")
    public Result addCollection(@PathVariable int user_id, @PathVariable int book_id)
    {
        return userBookService.addCollection(user_id,book_id);
    }
    
    @ApiOperation("UB2.从收藏夹中删除图书")
    @DeleteMapping("/users/{user_id}/collections/{book_id}")
    public Result removeCollection(@PathVariable int user_id, @PathVariable int book_id)
    {
        return userBookService.removeCollection(user_id, book_id);
    }
    
    @ApiOperation("UB3.检查用户是否收藏该图书")
    @GetMapping("/users/{user_id}/collections/{book_id}")
    public Result checkBookCollected(@PathVariable int user_id, @PathVariable int book_id)
    {
        return userBookService.checkBookCollected(user_id, book_id);
    }
    
    @ApiOperation("F1.添加收藏夹")
    @PostMapping("/users/{user_id}/favorites/{favorite_type}s/{favorite_id}")
    public Result addFavorite(
            @Pattern(regexp = "(author)|(book)",
                    message = "favorite_type ∈ {author, book}")
            @PathVariable String favorite_type,
            @PathVariable int user_id,
            @PathVariable int favorite_id)
    {
        return userBookService.addFavorite(favorite_type, user_id, favorite_id);
    }
    
    @ApiOperation("F2.删除收藏夹")
    @DeleteMapping("/users/{user_id}/favorites/{favorite_type}s/{favorite_id}")
    public Result removeFavorite(
            @Pattern(regexp = "(author)|(book)",
                    message = "favorite_type ∈ {author, book}")
            @PathVariable String favorite_type,
            @PathVariable int user_id,
            @PathVariable int favorite_id)
    {
        return userBookService.removeFavorite(favorite_type, user_id, favorite_id);
    }
    
    @ApiOperation("F3.检查是否添加收藏夹")
    @GetMapping("/users/{user_id}/favorites/{favorite_type}s/{favorite_id}")
    public Result checkFavorite(
            @Pattern(regexp = "(author)|(book)",
                    message = "favorite_type ∈ {author, book}")
            @PathVariable String favorite_type,
            @PathVariable int user_id,
            @PathVariable int favorite_id)
    {
        return userBookService.checkFavorite(favorite_type, user_id, favorite_id);
    }
    
    @ApiOperation("F4.获取收藏图书列表")
    @GetMapping("/users/{user_id}/favorites/books")
    public Result getFavoriteBooks(@PathVariable int user_id,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int per_page)
    {
        PageHelper.startPage(page, per_page);
        return userBookService.getFavoriteBooks(user_id);
    }
    
    @ApiOperation("F5.获取收藏作者列表")
    @GetMapping("/users/{user_id}/favorites/authors")
    public Result getFavoriteAuthors(@PathVariable int user_id,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int per_page)
    {
        PageHelper.startPage(page, per_page);
        return userBookService.getFavoriteAuthors(user_id);
    }
    
//    @ApiOperation("UB4.获取用户收藏图书")
//    @GetMapping("/users/{user_id}/collections")
//    public Result getCollections(int user_id, int page, int per_page)
//    {
//        return userBookService.getCollections(user_id, page, per_page);
//    }
}
