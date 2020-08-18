package com.project.Util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

    private static MD5Util md5Util = new MD5Util();
    private MD5Util(){};
    public static MD5Util getInstance(){
        return md5Util;
    }

    public String pass2MD5(String password){

        String encode = new Md5Hash(password, "qfloan", 3).toString();

        return encode;
    }
}
