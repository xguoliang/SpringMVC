/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2011-6-8  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.common;


import org.springframework.util.StringUtils;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2011-6-8
 */
public class StringLength {
    public static int getLength(String s) {
        int length = 0;
        if (StringUtils.hasText(s)) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < 128)
                    length++;
                else
                    length += 2;
            }
        }
        return length;
    }

}
