package cn.edu.scut.bookshop.config;

import cn.edu.scut.bookshop.BookshopApplicationTests;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EncryptTest extends BookshopApplicationTests
{
    @Autowired
    private StringEncryptor encryptor;
    
    @Test
    public void generateKeys()
    {
        String ENC_url = encryptor.encrypt("jdbc:mysql://116.56.140.131:3306/bookshop?requireSSL=true&useSSL=true&verifyServerCertificate=false&serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8");
        String ENC_username = encryptor.encrypt("root");
        String ENC_password = encryptor.encrypt("123456");
        System.out.printf("url: ENC(%s)\n", ENC_url);
        System.out.printf("username: ENC(%s)\n", ENC_username);
        System.out.printf("password: ENC(%s)\n", ENC_password);
        Assert.assertTrue(ENC_username.length() > 0);
        Assert.assertTrue(ENC_password.length() > 0);
    }
}
