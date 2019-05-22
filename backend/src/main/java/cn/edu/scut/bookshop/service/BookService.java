package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.dao.BookDao;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.*;
import cn.edu.scut.bookshop.util.BusinessException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService
{
    @Autowired
    private BookDao bookDao;
    
    public Result getBookDetail(int id)
    {
        BookDetail bookDetail = bookDao.getBookDetail(id);
        if (bookDetail == null)
            throw new BusinessException(ErrorCode.NOT_FOUND_BOOK_ID, id + "");
        return Result.OK().data(bookDetail).build();
    }
    
    public Result getBookCatagories()
    {
        List<BookCategory> bookCategories = bookDao.getBookCategories();
//        if (bookCategories == null)
//            throw new BusinessException(ErrorCode.NOT_FOUND_COMMON);
        return Result.OK().data(bookCategories).build();
        
    }
    
    public Result getBookCatagoriesBooks()
    {
        List<Map<String, Object>> bookCategories = bookDao.getAllBookCategories();
        for(Map<String, Object> categories: bookCategories)
        {
            PageHelper.startPage(1, 5);
            List<BookSimpleDetail> books = bookDao.getAllBookInCatagory((int)categories.get("id"));
            categories.put("books", books);
        }
        return Result.OK().data(bookCategories).build();
    }
    
    public Result getBookTagRelations(int id)
    {
        List<BookTag> bookTagRelations = bookDao.getBookTagRelations(id);
        if (bookTagRelations == null)
            throw new BusinessException(ErrorCode.NOT_FOUND_BOOK_ID, id + "");
        return Result.OK().data(bookTagRelations).build();
    }
    
    public Result getShortComments(int id)
    {
        List<String> shortComments = bookDao.getShortComments(id);
        if (shortComments == null)
            throw new BusinessException(ErrorCode.NOT_FOUND_COMMON);
        return Result.OK().data(shortComments).build();
        
    }
    
    public Result getAllBookInCatagory(int category_id)
    {
        List<BookSimpleDetail> bookSimpleDetails = bookDao.getAllBookInCatagory(category_id);
        if (bookSimpleDetails == null)
            throw new BusinessException(ErrorCode.NOT_FOUND_COMMON);
        return Result.OK().data(bookSimpleDetails).build();
    }
}
