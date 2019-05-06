package cn.edu.scut.bookshop.domain.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value="BookCategory")
public class BookCategory {
    private Integer id;

    private String title;

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
}