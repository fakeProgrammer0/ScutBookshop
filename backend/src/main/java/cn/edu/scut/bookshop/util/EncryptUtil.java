package cn.edu.scut.bookshop.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil
{
    public final static String KEY_SHA = "SHA";
    private final static int radix = 16;
    
    private static MessageDigest getMessageDigest(String key)
    {
        try
        {
            return MessageDigest.getInstance(key);
        } catch (NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String encrypt(String key, String message)
    {
        MessageDigest messageDigest = getMessageDigest(key);
        byte[] inputData = message.getBytes();
        messageDigest.update(inputData);
        BigInteger bigInteger = new BigInteger(messageDigest.digest());
        return bigInteger.toString(radix); // 转化为16位字符串
    }
    
    public static String encrypt(String message){
        return sha(message);
    }
    
    public static String sha(String message)
    {
        return encrypt(KEY_SHA, message);
    }
}
