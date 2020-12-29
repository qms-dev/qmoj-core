package team.akina.qmoj.dto;

public class QmojJudgementDTO {

    private String id;
    private int result;
    private String time;
    private String memory;
    private int language;
    private String lastTestCase;
    private String expectedOutput;
    private int totalCorrect;
    private int totalTestCases;
    private String error;
    private String errorDetail;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public int getResult() {
        return result;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
    public String getMemory() {
        return memory;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
    public int getLanguage() {
        return language;
    }

    public void setLastTestCase(String lastTestCase) {
        this.lastTestCase = lastTestCase;
    }
    public String getLastTestCase() {
        return lastTestCase;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalTestCases(int totalTestCases) {
        this.totalTestCases = totalTestCases;
    }
    public int getTotalTestCases() {
        return totalTestCases;
    }

    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
    public String getErrorDetail() {
        return errorDetail;
    }

}