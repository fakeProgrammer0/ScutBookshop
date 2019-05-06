package cn.edu.scut.bookshop.domain.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value = "BookTagRelation")
public class BookTagRelation {
    private Integer book_id;

    private Integer tag_id;

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}