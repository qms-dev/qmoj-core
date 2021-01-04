package team.akina.qmoj.dto;

import java.util.List;

/**
 * 题目内容的dto类，沿用chenbo以来的命名风格，添加Qmoj前缀
 */
public class QmojQuestionDTO {
    /**
     * 平台唯一题目ID
     */
    private long id;

    /**
     * 题目来源平台
     */
    private String platform;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目难度  (0/1/2对应easy/medium/hard)
     */
    private int difficulty;

    /**
     * 支持的语言类型
     */
    private List<Integer> languages;

    /**
     * 题目的标签，中文形式
     */
    private List<String> topicTags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Integer> languages) {
        this.languages = languages;
    }

    public List<String> getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(List<String> topicTags) {
        this.topicTags = topicTags;
    }
}
