package com.mongo.morphia.complex.common.text;

/**
 * 编码转换
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
public abstract class Encoding {
    public static final Encoding HEX = new Hex();

    /** 生成字符串s的编码 */
    public String encode(String s) {
        byte[] tmp = stringToUtf8(s);
        byte[] encoded = encode(tmp);
        return utf8ToString(encoded);
    }

    /** 将字符串转换为以UTF-8编码的字节数组 */
    public static byte[] stringToUtf8(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Encoding.stringToUtf8() failed!", e);
        }
    }

    /** 将以UTF-8编码的字节数组转换为字符串 */
    public static String utf8ToString(byte[] utf8) {
        try {
            return new String(utf8, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Encoding.utf8ToString() failed!", e);
        }
    }

    /** 对字符串进行编码，将其中对XML有特殊含义的字符转换为XML实体 */
    public static StringBuilder xmlEncode(String s) {
        return xmlEncode(s, new StringBuilder());
    }

    /** 对字符串进行编码，将其中对XML有特殊含义的字符转换为XML实体 */
    public static StringBuilder xmlEncode(String s, StringBuilder sb) {
        if (s == null)
            return sb;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\'':
                sb.append("&#39;");
                break;
            case '"':
                sb.append("&#34;");
                break;
            case '<':
                sb.append("&#60;");
                break;
            case '>':
                sb.append("&#62;");
                break;
            case '&':
                sb.append("&#38;");
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb;
    }

    /** 对编码字符串进行解码 */
    public String decode(String s) {
        byte[] tmp = stringToUtf8(s);
        byte[] decoded = decode(tmp);
        return utf8ToString(decoded);
    }

    /** 对数据编码 */
    public abstract byte[] encode(byte[] data);

    /** 对数据解码 */
    public abstract byte[] decode(byte[] data);

    /** s是否合格的名称，合法的名称以字母开头，仅含字母、数字与下划线 */
    public static boolean isQName(String s) {
        if (s.length() == 0)
            return false;
        char c = s.charAt(0);
        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
            return false;

        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if (c >= 'a' && c <= 'z')
                continue;
            if (c >= 'A' && c <= 'Z')
                continue;
            if (c >= '0' && c <= '9')
                continue;
            if (c == '_')
                continue;
            return false;
        }
        return true;
    }

}