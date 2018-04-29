package me.zonglun.seckilling.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author : Administrator
 * @create 2018-04-29 17:51
 */
public class MD5Utils {


    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String SALT = "1a3c5f9dNg0";

    public static String inputPassFormPass(String inputPass) {
       String str =  "" + SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4) + SALT;
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
            String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
            return md5(str);
    }

    public static String inputToDBPass(String input, String saltDB) {
        String formPass = inputPassFormPass(input);
        return formPassToDBPass(formPass, saltDB);
    }

    public static void main(String[] args) {
         System.out.println(inputPassFormPass("123456"));
        System.out.println(inputToDBPass("123456","1a2b3d4c"));
    }
}
