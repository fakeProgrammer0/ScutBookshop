package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

@Data
public class LoginUser
{
    protected int id;
    protected String username;
    protected String password;
}
