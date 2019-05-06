package cn.edu.scut.bookshop.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShortComment {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private Integer book_id;

    @JsonIgnore
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}