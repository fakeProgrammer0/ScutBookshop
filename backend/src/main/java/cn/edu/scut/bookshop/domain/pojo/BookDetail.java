package cn.edu.scut.bookshop.domain.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDetail
{
    private int id;
    private String title;
    private String original;
    
    private int author_id; // 作者id
    private String author;
    private String translator;
    
    private String press;
    private String publish_date; // 用string存储即可
    private int pages;
    private BigDecimal price; // 数值型
    private String binding;
    private String ISBN;
    private double douban_score;
    private String book_url;
    private String pic_url;
    private String summary;
    private String author_intro;
    
    @JsonProperty("ISBN")
    public String getISBN()
    {
        return ISBN;
    }
    
    @JsonProperty("ISBN")
    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }
}
