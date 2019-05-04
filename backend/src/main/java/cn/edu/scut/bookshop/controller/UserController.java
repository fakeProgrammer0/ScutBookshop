package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.LoginUser;
import cn.edu.scut.bookshop.domain.pojo.UserInfo;
import cn.edu.scut.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping("/users/")
    public Result signUp(@RequestBody UserInfo userInfo)
    {
        return userService.signUp(userInfo);
    }
    
    @PostMapping("/users/login")
    public Result login(@RequestBody LoginUser user)
    {
        return userService.login(user);
    }
}
