package cn.edu.scut.bookshop.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookTag {
    private Integer id;

    private String title;

    @JsonIgnore
    private Integer category_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}