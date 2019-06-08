package cn.edu.scut.bookshop.dao;

import cn.edu.scut.bookshop.domain.pojo.Author;
import cn.edu.scut.bookshop.domain.pojo.AuthorOverview;
import cn.edu.scut.bookshop.domain.pojo.BookSimpleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
@Mapper
@Repository
public interface UserBookDao
{
    int insertCollection(int user_id, int book_id);
    
    int deleteCollection(int user_id, int book_id);
    
    int checkBookCollected(int user_id, int book_id);
    
    int insertFavorite(String favorite_type, int user_id, int favorite_id);
    int deleteFavorite(String favorite_type, int user_id, int favorite_id);
    int checkFavorite(String favorite_type, int user_id, int favorite_id);
    
    int getFavorites(String favorite_type, int user_id, int favorite_id);
    List<BookSimpleDetail> getFavoriteBooks(int user_id);
    List<AuthorOverview> getFavoriteAuthors(int user_id);
}
