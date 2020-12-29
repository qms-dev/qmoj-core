package team.akina.qmoj.entity;

    public class QmojQuestionWithBLOBs extends QmojQuestion {
    private String content;

    private String langToValidPlayground;

    private String topicTags;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLangToValidPlayground() {
        return langToValidPlayground;
    }

    public void setLangToValidPlayground(String langToValidPlayground) {
        this.langToValidPlayground = langToValidPlayground == null ? null : langToValidPlayground.trim();
    }

    public String getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(String topicTags) {
        this.topicTags = topicTags == null ? null : topicTags.trim();
    }
}