package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.Comment;
import cn.edu.scut.bookshop.service.UserCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author: May
 **/
@Api
@RestController
public class UserCommentController {
    @Autowired
    private UserCommentService userCommentService;


    @ApiOperation("用户添加对书本的评论")
    @PostMapping("/user/comment/create")
    public Result createComment(@RequestBody Comment comment){
        return userCommentService.creatComment(comment);
    }

    @ApiOperation("用户修改对书本的评论")
    @PutMapping("/user/comment/{id}/update")
    public Result updateComment( @PathVariable Integer id,@RequestBody Comment comment){
        return userCommentService.updateComment(id,comment);
    }


    @ApiOperation("批量删除对书本的评论")
    @PostMapping("/user/comment/delete")
    public Object deleteComment( @RequestParam("ids") List<Integer> ids){
        return userCommentService.deleteComment(ids);
    }
}
