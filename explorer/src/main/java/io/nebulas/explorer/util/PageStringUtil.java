package io.nebulas.explorer.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Page string util
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
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
}
