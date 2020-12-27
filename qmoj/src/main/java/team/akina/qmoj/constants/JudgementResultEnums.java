package team.akina.qmoj.constants;

/**
 * qmoj的判题结果枚举
 */
public enum JudgementResultEnums {
    AC(0, "Accepted"),
    WA(1, "Wrong Answer"),
    CE(2, "Compile Error"),
    RE(3, "Runtime Error"),
    TLE(4, "Time Limit Exceeded"),
    MLE(5, "MLE"),
    Unknown(99, "Unknown Error Code");

    private String name;
    private int code;

    JudgementResultEnums(int code, String name) {
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
        for (JudgementResultEnums result : JudgementResultEnums.values()) {
            if (result.getCode() == code) {
                return result.getName();
            }
        }
        return "";
    }
}
