package cn.edu.scut.bookshop.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: May
 **/
//获取某一类别下所有书籍时返回的简单的书籍详情

    @Data
public class BookSimpleDetail {
    private int id;
    private String title;
    private String author;
    private BigDecimal price; // 数值型
    private double douban_score;
    private String pic_url;

    @JsonIgnore
    private String ISBN;



}
