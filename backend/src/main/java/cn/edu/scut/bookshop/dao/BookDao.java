package cn.edu.scut.bookshop.dao;

import cn.edu.scut.bookshop.domain.pojo.BookDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BookDao
{
    BookDetail getBookDetail(@Param("id") int id);
}
