package cn.edu.scut.bookshop.dao;

import cn.edu.scut.bookshop.domain.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao
{
    User getUserById(@Param("id") int id);
    
    User getUserByUsername(@Param("username") String username);
    
    int insertUser(User user);
}
