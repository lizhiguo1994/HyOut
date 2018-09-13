package com.hyout.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/8/24.
 */
public class MD5Util {
    /**
     * MD5加密
     * @param str 需要加密的值
     * @return 加密完成的值(小写)
     */
    public static String MD5(String str){
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try {
                md.update(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
}
