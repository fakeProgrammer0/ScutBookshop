package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.LoginUser;
import cn.edu.scut.bookshop.domain.pojo.User;
import cn.edu.scut.bookshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    
    @ApiOperation("U1.用户注册")
    @PostMapping("/users")
    public Result signUp(@Validated @RequestBody User user)
    {
        return userService.signUp(user);
    }
    
    @ApiOperation("U2.用户登录")
    @PostMapping("/users/login")
    public Result login(@Validated @RequestBody LoginUser user)
    {
        return userService.login(user);
    }
}
