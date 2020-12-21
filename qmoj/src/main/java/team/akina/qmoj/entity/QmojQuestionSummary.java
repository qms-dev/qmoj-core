package team.akina.qmoj.entity;

public class QmojQuestionSummary {
    private Long id;

    private String title;

    private Byte difficulty;

    private String questionTags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionTags() {
        return questionTags;
    }

    public void setQuestionTags(String questionTags) {
        this.questionTags = questionTags == null ? null : questionTags.trim();
    }
}