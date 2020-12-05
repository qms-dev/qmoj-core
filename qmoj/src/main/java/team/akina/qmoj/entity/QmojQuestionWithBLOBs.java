package team.akina.qmoj.entity;

public class QmojQuestionWithBLOBs extends QmojQuestion {
    private String content;

    private String langToValidPlayground;

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
}