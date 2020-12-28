package team.akina.qmoj.param;

/**
 * 用来映射前端提交的题解
 * todo 引入注解验证的方式简化Param验证过程
 */
public class QmojAnswerParam {


    /**
     * 提交的题目的id
     */
    private long id;

    /**
     * 提交的题解内容
     */
    private String answer;

    /**
     * 提交题解的语言类型
     */
    private int language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
