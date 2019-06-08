package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.dao.UserCommentDao;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.Comment;
import cn.edu.scut.bookshop.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: May
 **/

@Service
public class UserCommentService {

    @Autowired
    private UserCommentDao userCommentDao;

    public Result creatComment(Comment bookComment){
        bookComment.setCreate_time(new Date());
        int count= userCommentDao.creatComment(bookComment);

        if(count<=0){
            throw new BusinessException(ErrorCode.BAD_REQUEST_COMMON);
        }
        return Result.OK().data(bookComment).build();
    }

    //修改评论
    public Result updateComment( Integer id,Comment bookComment){
        bookComment.setCreate_time(new Date());
        bookComment.setId(id);
        int count=userCommentDao.updateComment(bookComment);
        if(count<=0){
            throw new BusinessException(ErrorCode.BAD_REQUEST_COMMON);
        }
            return Result.OK().data(bookComment).build();

    }

    //批量删除评论
    public Object deleteComment(List<Integer> ids){
        int count=userCommentDao.deleteComment(ids);
        if(count<=0){
            throw new BusinessException(ErrorCode.BAD_REQUEST_COMMON);
        }
        return Result.OK();

    }

    //删除评论
//    public Object deleteComment( List<Integer> ids){
//        int count=userCommentDao.delete(ids);
//        if(count>0){
//            return Result.OK();
//        }
//        else{
//            throw new BusinessException(ErrorCode.BAD_REQUEST_COMMON);
//        }
//    }
}
