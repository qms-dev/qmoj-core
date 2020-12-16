package team.akina.qmoj.pojo;

/**
 * @program: qmoj
 * @description: 封装一个TopicTag类用于存放标签
 * @author: liu yan
 * @create: 2020-12-09 23:58
 */
public class QmojTopicTag {
    private  String topictag_name;
    private  String topictag_slug;
    private  String topictag_translatedName;

    public QmojTopicTag() {
    }

    public QmojTopicTag(String topictag_name, String topictag_slug, String topictag_translatedName) {
        this.topictag_name = topictag_name;
        this.topictag_slug = topictag_slug;
        this.topictag_translatedName = topictag_translatedName;
    }

    public String getTopictag_name() {
        return topictag_name;
    }

    public void setTopictag_name(String topictag_name) {
        this.topictag_name = topictag_name;
    }

    public String getTopictag_slug() {
        return topictag_slug;
    }

    public void setTopictag_slug(String topictag_slug) {
        this.topictag_slug = topictag_slug;
    }

    public String getTopictag_translatedName() {
        return topictag_translatedName;
    }

    public void setTopictag_translatedName(String topictag_translatedName) {
        this.topictag_translatedName = topictag_translatedName;
    }
}