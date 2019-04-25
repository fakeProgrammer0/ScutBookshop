package cn.edu.scut.bookshop.domain.pojo;

import lombok.Data;

@Data
public class Book
{
    private int id;
    private String title;
    private String subject_url;
    private String pic_url;
    private String author;
    private String press;
    private String original;
    private String translator;
    private String publishDate;
    private int pages;
    private String price;
    private String bingding;
    private String ISBN;
    private double douban_score;
    private int scoreNum;
    private String summary;
    private String author_intro;
}
