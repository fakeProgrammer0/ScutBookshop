package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.dao.BookDao;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.BookDetail;
import cn.edu.scut.bookshop.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    @Autowired
    private BookDao bookDao;
    
    public Result getBookDetail(int id)
    {
        BookDetail bookDetail = bookDao.getBookDetail(id);
        if(bookDetail == null)
            throw new BusinessException(ErrorCode.NOT_FOUND_BOOK_ID, id + "");
        return Result.OK().data(bookDetail).build();
    }
}
