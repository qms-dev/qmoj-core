package team.akina.qmoj.entity;

import java.util.Date;

public class QmojQuestionStatus {
    private Long id;

    private Integer questionId;

    private Integer totalAccepted;

    private Integer totalSubmission;

    private Long acRate;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getTotalAccepted() {
        return totalAccepted;
    }

    public void setTotalAccepted(Integer totalAccepted) {
        this.totalAccepted = totalAccepted;
    }

    public Integer getTotalSubmission() {
        return totalSubmission;
    }

    public void setTotalSubmission(Integer totalSubmission) {
        this.totalSubmission = totalSubmission;
    }

    public Long getAcRate() {
        return acRate;
    }

    public void setAcRate(Long acRate) {
        this.acRate = acRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}