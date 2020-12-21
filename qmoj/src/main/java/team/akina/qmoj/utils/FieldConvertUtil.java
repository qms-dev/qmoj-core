package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * DTOMapper用于转换字典
 */
public class FieldConvertUtil {
    /**
     * 转换语言列表
     *
     * @param languages
     * @return
     */
    public static List<Integer> convertJsonToIntegerList(String languages) {
        return JSON.parseArray(languages, Integer.class);
    }

    /**
     * 转换标签列表
     *
     * @param tags
     * @return
     */
    public static List<String> convertJsonToStringList(String tags) {
        return JSON.parseArray(tags, String.class);
    }
}
