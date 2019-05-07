package cn.edu.scut.bookshop.dao;

import cn.edu.scut.bookshop.domain.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface BookDao
{
    BookDetail getBookDetail(@Param("id") int id);
    List<BookCategory> getBookCategories();
    List<BookTag> getBookTagRelations(@Param("id") int id);
    List<String>getShortComments(@Param("id") int id);
    List<BookSimpleDetail>getAllBookInCatagory(@Param("category_id") int category_id);
    
    List<Map<String, Object>> getAllBookCategories();
}
