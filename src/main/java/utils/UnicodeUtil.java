package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 印佳明
 * @create 2017-11-27 18:13
 */
public class UnicodeUtil
{
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }
}
