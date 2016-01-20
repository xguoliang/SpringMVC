package com.mongo.morphia.complex.common.text;

/**
 * 十六进制编码
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
class Hex extends Encoding {
    /** 0~15的编码字符 */
    private static byte[] hex = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };

    /** 十六进制字符对应的二进制位 */
    private static int[] bits;
    static {
        bits = new int[256];
        int i, j;
        for (i = 0; i < 256; i++) {
            bits[i] = 0x7FFFFFFF;
        }

        for (i = 0; i <= 9; i++) {
            bits['0' + i] = i;// 数字0~9
        }
        for (i = 0, j = 10; i <= 5; i++, j++) {
            bits['a' + i] = j;// 添加小写字母a-f
            bits['A' + i] = j;// 添加大写字母A-F
        }
    }

    /** 字符串是否十六进制字符串 */
    public static boolean isHexString(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')
                continue;
            if (c >= 'A' && c <= 'F')
                continue;
            if (c >= 'a' && c <= 'f')
                continue;
            return false;
        }
        return true;
    }

    /** 十六进制编码 */
    @Override
    public byte[] encode(byte[] data) {
        byte[] buffer = new byte[data.length << 1];
        for (int i = 0, j = 0; i < data.length; i++) {
            buffer[j++] = hex[(data[i] >> 4) & 15];
            buffer[j++] = hex[data[i] & 15];
        }
        return buffer;
    }

    /** 十六进制解码 */
    @Override
    public byte[] decode(byte[] data) {
        byte[] buffer = new byte[data.length >> 1];
        for (int i = 0, j = 0; i < buffer.length; i++) {
            int k = bits[data[j++]] << 4;
            k += bits[data[j++]];
            buffer[i] = (byte) k;
        }
        return buffer;
    }

    public static void main(String[] args) {
        String s = "abc在地";
        String t = HEX.encode(s);

        System.out.println(t);
        System.out.println(HEX.decode(t));
    }
}