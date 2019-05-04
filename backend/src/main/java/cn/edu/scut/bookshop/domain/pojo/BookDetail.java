package cn.edu.scut.bookshop.domain.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookDetail
{
    private int id;
    private String title;
    private String original;
    
    private String author;
    private String translator;
    private int author_id; // 作者id
    
    private String press;
    private String publish_date; // 用string存储即可
    private int pages;
    private String price;
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
