package team.akina.qmoj.constants;

/**
 * LeetCode判题结果的枚举，还需要补全，缺少一部分错误类型
 */
public enum LeetCodeJudgementStatusEnums {
    AC(10, "Accepted"),
    WA(11, "Wrong Answer"),
    TLE(14, "Time Limit Exceeded"),
    RE(15, "Runtime Error"),
    CE(20, "Compile Error");

    private String name;
    private int code;

    LeetCodeJudgementStatusEnums(int code, String name) {
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
        for (LeetCodeJudgementStatusEnums status : LeetCodeJudgementStatusEnums.values()) {
            if (status.getCode() == code) {
                return status.getName();
            }
        }
        return "";
    }
}
