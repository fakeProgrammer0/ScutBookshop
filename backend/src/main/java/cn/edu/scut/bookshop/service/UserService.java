package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.dao.UserDao;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.domain.pojo.LoginUser;
import cn.edu.scut.bookshop.domain.pojo.User;
import cn.edu.scut.bookshop.util.BusinessException;
import cn.edu.scut.bookshop.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserDao userDao;
    
    public Result signUp(User user)
    {
        User tempUser = userDao.getUserByUsername(user.getUsername());
        if (tempUser != null)
            throw new BusinessException(ErrorCode.PARAM_ERR_USERNAME_REGISTERED_EXISTS, user.getUsername());
        // 对密码加密，SHA
        String encryptedPassword = EncryptUtil.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        int affectedRow = userDao.insertUser(user);
        return Result.OK().build();
    }
    
    // TODO: 缓存存redis
    public Result login(LoginUser user)
    {
        User tempUser = userDao.getUserByUsername(user.getUsername());
        BusinessException ex = new BusinessException(ErrorCode.NOT_FOUND_USERNAME_OR_PASSWORD_INVALID);
        if (tempUser == null)
            throw ex;
        String encryptedPassword = EncryptUtil.encrypt(user.getPassword());
        if (!tempUser.getPassword().equals(encryptedPassword))
            throw ex;
        tempUser.setPassword(null);
        Map<String, Object> data = new HashMap<>();
        data.put("user", tempUser);
        return Result.OK().data(data).build();
    }
}
