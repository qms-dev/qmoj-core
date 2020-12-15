package team.akina.qmoj.pojo;

/**
 * @program: qmoj
 * @description: 用来存CTO想要的stat_status键值对
 * @author: liu yan
 * @create: 2020-12-08 00:16
 */
public class QmojStatStatusPairs {
    private  int level;
    private String question__title_slug;

    public QmojStatStatusPairs(int level, String question__title_slug, long question_id) {
        this.level = level;
        this.question__title_slug = question__title_slug;
        this.question_id = question_id;
    }

    public QmojStatStatusPairs() {
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQuestion__title_slug() {
        return question__title_slug;
    }

    public void setQuestion__title_slug(String question__title_slug) {
        this.question__title_slug = question__title_slug;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public int getLevel() {
        return level;
    }

    private  long question_id;
}