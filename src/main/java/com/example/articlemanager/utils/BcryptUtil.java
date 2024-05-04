
package com.example.articlemanager.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BcryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 使用 BCrypt 算法對字符串進行加密
     *
     * @param str 需要加密的字符串
     * @return 加密後的字符串
     */
    public static String encode(String str) {
        return encoder.encode(str);
    }

    /**
     * 驗證原始字符串和加密後的字符串是否匹配
     *
     * @param rawStr     原始字符串
     * @param encodedStr 加密後的字符串
     * @return 匹配返回 true，否則返回 false
     */
    public static boolean matches(String rawStr, String encodedStr) {
        return encoder.matches(rawStr, encodedStr);
    }
}