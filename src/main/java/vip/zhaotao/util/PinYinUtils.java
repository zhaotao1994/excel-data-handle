package vip.zhaotao.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 拼音工具类
 */
public class PinYinUtils {

    /**
     * 获取拼音
     *
     * @param chinese
     * @return
     */
    private static String getPinyin(String chinese, Type type) throws BadHanyuPinyinOutputFormatCombination {
        char[] charArray = chinese.trim().toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        StringBuilder builder = new StringBuilder();
        String temp;
        try {
            for (int i = 0; i < charArray.length; i++) {
                temp = String.valueOf(charArray[i]);
                if (temp.matches("[\u4e00-\u9fa5]+")) {
                    String pinyin = PinyinHelper.toHanyuPinyinStringArray(charArray[i], format)[0];
                    switch (type) {
                        case INITIAL:
                            builder.append(pinyin.toCharArray()[0]);
                            break;
                        case COMPLETE:
                            builder.append(pinyin);
                            break;
                    }
                } else if (temp.matches("[0-9]+") || temp.matches("[a-zA-Z]+")) {
                    builder.append(charArray[i]);
                } else {
                    builder.append(charArray[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw e;
        }
        return builder.toString();
    }

    /**
     * 获取首字母
     *
     * @param chinese
     * @return
     */
    public static String getInitial(String chinese) throws BadHanyuPinyinOutputFormatCombination {
        return getPinyin(chinese, Type.INITIAL);
    }

    /**
     * 获取全拼
     *
     * @param chinese
     * @return
     */
    public static String getComplete(String chinese) throws BadHanyuPinyinOutputFormatCombination {
        return getPinyin(chinese, Type.COMPLETE);
    }

    enum Type {
        /**
         * 首字母
         */
        INITIAL,
        /**
         * 全拼
         */
        COMPLETE
    }
}
