package team.akina.qmoj.constants;


/**
 * 代表题目来源平台的枚举
 */
public enum PlatformEnums {
    QMOJ(0, "QMOJ"),
    LEETCODE(1, "LeetCode");

    private String name;
    private int code;

    PlatformEnums(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据指定枚举的code查找对应名称，若未找到，返回空字符串
     */
    public static String getName(int code) {
        for (PlatformEnums platform : PlatformEnums.values()) {
            if (platform.getCode() == code) {
                return platform.getName();
            }
        }
        return "";
    }
}
