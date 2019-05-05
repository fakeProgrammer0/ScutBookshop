package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginUser
{
    protected int id;
    @Size(min = 2, max = 30, message = "用户名合法长度为2-30个字符")
    protected String username;
    @NotBlank
    protected String password;
}
