package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

@Data
public class UserInfo extends LoginUser
{
    private int age;
    private int gender;
}
