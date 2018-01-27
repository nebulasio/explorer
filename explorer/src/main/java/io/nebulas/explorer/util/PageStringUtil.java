package io.nebulas.explorer.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/1/27
 */
public class PageStringUtil {


    public static String replace(String str, int length, String append) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() < length) {
            return str;
        }
        return str.substring(0, length) + append;
    }

    public static void main(String[] args) {
        String s = "abcdef";
        System.out.println(PageStringUtil.replace(s, 2, "..."));
    }
}
