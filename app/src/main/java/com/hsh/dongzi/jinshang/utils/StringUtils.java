package com.hsh.dongzi.jinshang.utils;

import java.util.regex.Pattern;

/**
 * Created by lhk on 2018/1/10.
 */

public class StringUtils {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(166)|(17[013678])|(18([0-9])))\\d{8}$";;

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean checkPhone(String str) {
        return Pattern.matches(REGEX_MOBILE, str);
    }

}
