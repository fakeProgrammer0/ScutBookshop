package cn.edu.scut.bookshop.dao;

import cn.edu.scut.bookshop.domain.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: May
 **/
@Component
@Mapper
@Repository
public interface UserCommentDao {
    int creatComment(Comment bookComment);


    int updateComment(Comment bookComment);


    int deleteComment(List<Integer> ids);


}
