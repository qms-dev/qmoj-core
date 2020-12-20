package team.akina.qmoj.constants;


/**
 * 代表题目来源平台的枚举
 */
public enum PlatformNums {
    QMOJ(0, "QMOJ"),
    LEETCODE(1, "LeetCode");

    private String name;
    private int code;

    PlatformNums(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getName(int code) {
        for (PlatformNums c : PlatformNums.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return "";
    }
}
