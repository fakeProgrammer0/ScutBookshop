package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class User extends LoginUser
{
    @Min(-1)
    @Max(200)
    private int age;
    
    @Min(0)
    @Max(2)
    private int gender;
}
