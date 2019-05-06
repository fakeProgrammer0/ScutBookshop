package cn.edu.scut.bookshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Mapper
@Repository
public interface UserBookDao
{
    int insertCollection(int user_id, int book_id);
    
    int deleteCollection(int user_id, int book_id);
    
    int checkBookCollected(int user_id, int book_id);
    
    
}
