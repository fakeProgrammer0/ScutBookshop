package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.dao.UserBookDao;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.util.BusinessException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService
{
    @Autowired
    private UserBookDao userBookDao;
    
    public Result addCollection(int user_id, int book_id)
    {
        int affectedRow = userBookDao.insertCollection(user_id, book_id);
        assert affectedRow == 1;
        return Result.OK().build();
    }
    
    public Result removeCollection(int user_id, int book_id)
    {
        int affectedRow = userBookDao.deleteCollection(user_id, book_id);
        if (affectedRow == 1)
            return Result.OK().build();
        throw new BusinessException(ErrorCode.NOT_FOUND_COLLECTION);
    }
    
    public Result checkBookCollected(int user_id, int book_id)
    {
        int count = userBookDao.checkBookCollected(user_id, book_id);
        if(count == 1)
            return Result.OK().build();
        throw new BusinessException(ErrorCode.NOT_FOUND_COLLECTION);
    }
    
    public Result getCollections(int user_id, int page, int per_page)
    {
        PageHelper.startPage(page, per_page);
        return null;
    }
    
    public Result addFavorite(String favorite_type, int user_id, int favorite_id)
    {
        int affectedRow = userBookDao.insertFavorite(favorite_type, user_id, favorite_id);
        assert affectedRow == 1;
        return Result.OK().build();
    }
    
    public Result removeFavorite(String favorite_type, int user_id, int favorite_id)
    {
        int affectedRow = userBookDao.deleteFavorite(favorite_type, user_id, favorite_id);
        if (affectedRow == 1)
            return Result.OK().build();
        throw new BusinessException(ErrorCode.NOT_FOUND_FAVORITE);
    }
    
    public Result checkFavorite(String favorite_type, int user_id, int favorite_id)
    {
        int count = userBookDao.checkFavorite(favorite_type, user_id, favorite_id);;
        if(count == 1)
            return Result.OK().build();
        throw new BusinessException(ErrorCode.NOT_FOUND_FAVORITE);
    }
    
    public Result getFavoriteBooks(int user_id)
    {
        return Result.OK().data(userBookDao.getFavoriteBooks(user_id)).build();
    }
 
    public Result getFavoriteAuthors(int user_id)
    {
        return Result.OK().data(userBookDao.getFavoriteAuthors(user_id)).build();
    }
    
    
}
