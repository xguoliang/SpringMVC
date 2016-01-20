package Until.Pinyin;
/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2011-5-18  guolei_mao  创建。
 * 
 */



import java.util.ArrayList;
import java.util.List;

import Until.StringUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2011-5-18
 */
public class PinyinUtils {
    /**
     * 将字符串转换成拼音数组
     * 
     * @param s
     * @return
     */
    public static List<String> stringToPinyin(String s) {
        return stringToPinyin(s, false, null, true, false);
    }

    /**
     * 将字符串转换成拼音数组
     * 
     * @param s
     * @return
     */
    public static List<String> stringToPinyin(String s, String separator) {
        return stringToPinyin(s, true, separator, true, false);
    }

    /**
     * 将字符串转换成拼音数组
     * 
     * @param s
     * @param isPolyphone
     *            是否查出多音字的所有拼音
     * @param separator
     *            多音字拼音之间的分隔符
     * @return
     */
    public static List<String> stringToPinyin(String s, boolean isPolyphone, String separator, boolean headIsCapital,
            boolean isCapital) {
        // 判断字符串是否为空
        if ("".equals(s) || null == s) {
            return null;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        List<String> list = new ArrayList<String>(length);

        for (int i = 0; i < length; i++) {
            list.add(charToPinyin(chars[i], isPolyphone, separator, headIsCapital, isCapital, true));
        }
        return list;
    }

    /**
     * 将单个字符转换成拼音
     * 
     * @param c
     * @return
     */
    public static String charToPinyin(char c, boolean isPolyphone, String separator, boolean headIsCapital,
            boolean isCapital, boolean distinct) {
        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        if (isCapital)
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        else
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer tempPinyin = new StringBuffer();

        // 如果是中文
        if (c > 128) {
            try {
                // 转换得出结果
                String[] strs = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                if (strs != null) {
                    if (distinct) {
                        strs = distinct(strs);
                    }

                    // 是否查出多音字，默认是查出多音字的第一个字符
                    if (isPolyphone && null != separator) {
                        for (int i = 0; i < strs.length; i++) {
                            if (headIsCapital) {
                                strs[i] = StringUtils.headCharUpperCase(strs[i]);
                            } else {
                                strs[i] = StringUtils.headCharLowerCase(strs[i]);
                            }
                            tempPinyin.append(strs[i]);
                            if (strs.length != (i + 1)) {
                                // 多音字之间用特殊符号间隔起来
                                tempPinyin.append(separator);
                            }
                        }
                    } else {
                        if (headIsCapital) {
                            strs[0] = StringUtils.headCharUpperCase(strs[0]);
                        } else {
                            strs[0] = StringUtils.headCharLowerCase(strs[0]);
                        }
                        tempPinyin.append(strs[0]);
                    }
                } else {
                    tempPinyin.append(c);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            if (headIsCapital) {
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }
            tempPinyin.append(c);
        }

        return tempPinyin.toString();

    }

    private static String[] distinct(String[] ss) {
        List<String> list = new ArrayList<String>(ss.length);
        for (String s : ss) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 取汉字的首字母
     * 
     * @param c
     * @param isCapital
     *            是否是大写
     * @return
     */
    public static char[] getHeadByChar(char c, boolean isCapital, boolean distinct) {
        // 如果不是汉字直接返回
        if (c <= 128) {
            if (isCapital) {
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }
            return new char[] { c };
        }

        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        if (isCapital)
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        else
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        // 获取所有的拼音
        String[] pinyingStr = null;
        try {
            pinyingStr = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        if (pinyingStr == null) {
            return new char[] { c };
        }
        if (distinct) {
            pinyingStr = distinct(pinyingStr);
        }
        // 创建返回对象
        int polyphoneSize = pinyingStr.length;
        char[] headChars = new char[polyphoneSize];
        int i = 0;
        // 截取首字符
        for (String s : pinyingStr) {
            char headChar = s.charAt(0);
            // 首字母是否大写，默认是小写
            headChars[i] = headChar;
            i++;
        }

        return headChars;
    }

    /**
     * 取汉字的首字母(默认是大写)
     * 
     * @param c
     * @return
     */
    public static char[] getHeadByChar(char c) {
        return getHeadByChar(c, true, true);
    }

    /**
     * 查找字符串首字母
     * 
     * @param s
     * @return
     */
    public static List<String> getHeadByString(String s) {
        return getHeadByString(s, true);
    }

    /**
     * 查找字符串首字母
     * 
     * @param s
     * @param isCapital
     *            是否大写
     * @return
     */
    public static List<String> getHeadByString(String s, boolean isCapital) {
        return getHeadByString(s, isCapital, null, true);
    }

    /**
     * 查找字符串首字母
     * 
     * @param s
     * @param isCapital
     *            是否大写
     * @param separator
     *            分隔符
     * @return
     */
    public static List<String> getHeadByString(String s, boolean isCapital, String separator, boolean distinct) {
        char[] chars = s.toCharArray();
        List<String> headString = new ArrayList<String>(chars.length);
        int i = 0;
        for (char ch : chars) {
            char[] chs = getHeadByChar(ch, isCapital, distinct);
            StringBuffer sb = new StringBuffer();
            if (null != separator) {
                int j = 1;

                for (char ch1 : chs) {
                    sb.append(ch1);
                    if (j != chs.length) {
                        sb.append(separator);
                    }

                    j++;
                }
            } else {
                sb.append(chs[0]);
            }
            headString.add(sb.toString());
            i++;
        }
        return headString;
    }
    
    public static List<String> stringToPinyinCombination(String s) {
        if (StringUtils.isEmpty(s))
            return null;

        String separator = ",";
        boolean containSeparator = s.contains(",");
        if (containSeparator) {
            s = s.replace(",", "$MGL$");
        }
        List<String> pys = PinyinUtils.stringToPinyin(s, separator);

        // 组合算法 by mgl 2011-5-19
        List<String[]> lsa = new ArrayList<String[]>();
        int size = 1;
        for (String py : pys) {
            String[] sa = py.split(separator);
            lsa.add(sa);
            size *= sa.length;
        }
        List<String> pinYin = new ArrayList<String>(size);
        for (int j = 0; j < size; j++) {
            pinYin.add("");
        }

        int current = size;
        for (int i = 0; i < lsa.size(); i++) {
            String[] sa = lsa.get(i);
            current /= sa.length;
            int m = 0;
            for (int n = 0; n < size;) {
                for (int j = 0; j < current; j++, n++) {
                    String temp = pinYin.get(n);
                    temp += sa[m];
                    if (containSeparator) {
                        temp = temp.replace("$MGL$", ",");
                    }
                    pinYin.set(n, temp);
                }
                m++;
                m = m % sa.length;
            }
        }
        return pinYin;
    }
    
    public static List<String> getHeadByStringCombination(String s) {
        if (StringUtils.isEmpty(s))
            return null;

        String separator = ",";
        boolean containSeparator = s.contains(",");
        if (containSeparator) {
            s = s.replace(",", "$MGL$");
        }
        List<String> pys = PinyinUtils.getHeadByString(s, true, separator, true);

        // 组合算法 by mgl 2011-5-19
        List<String[]> lsa = new ArrayList<String[]>();
        int size = 1;
        for (String py : pys) {
            String[] sa = py.split(separator);
            lsa.add(sa);
            size *= sa.length;
        }
        List<String> pinYin = new ArrayList<String>(size);
        for (int j = 0; j < size; j++) {
            pinYin.add("");
        }

        int current = size;
        for (int i = 0; i < lsa.size(); i++) {
            String[] sa = lsa.get(i);
            current /= sa.length;
            int m = 0;
            for (int n = 0; n < size;) {
                for (int j = 0; j < current; j++, n++) {
                    String temp = pinYin.get(n);
                    temp += sa[m];
                    if (containSeparator) {
                        temp = temp.replace("$MGL$", ",");
                    }
                    pinYin.set(n, temp);
                }
                m++;
                m = m % sa.length;
            }
        }
        return pinYin;
    }

    public static void main(String[] args) {
        List<String> ss = PinyinUtils.getHeadByString("王红军".substring(0, 1), true, ",", true);
        for (String string : ss) {
            System.out.println(string);
        }
        
        ss = PinyinUtils.getHeadByString("王红军", true, ",", true);
        for (String string : ss) {
            System.out.println(string);
        }
        
        ss = PinyinUtils.getHeadByStringCombination("曾乐");
        for (String string : ss) {
            System.out.println(string);
        }

        ss = PinyinUtils.stringToPinyin("曾巩俐", " ");
        for (String string : ss) {
            System.out.println(string);
        }

        ss = PinyinUtils.stringToPinyin("毛国雷（金蝶）");
        for (String string : ss) {
            System.out.println(string);
        }
        
        ss = PinyinUtils.stringToPinyinCombination("曾,乐");
        for (String string : ss) {
            System.out.println(string);
        }
    }
}
