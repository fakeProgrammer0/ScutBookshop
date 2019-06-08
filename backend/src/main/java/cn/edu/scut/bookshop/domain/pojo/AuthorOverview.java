package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

@Data
public class AuthorOverview
{
    private int id;
    
    private String name;
    
    private String original;
    
    private String country;
    
    private String pic_url;
}
