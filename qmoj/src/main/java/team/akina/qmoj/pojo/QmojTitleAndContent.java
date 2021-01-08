package team.akina.qmoj.pojo;

import java.util.List;
public class QmojTitleAndContent {
    private  String title;
    private  String Content;
    private  List<QmojTopicTag> topicTags;

    public QmojTitleAndContent(String title, String content, List<QmojTopicTag> topicTags) {
        this.title = title;
        Content = content;
        this.topicTags = topicTags;
    }

    public List<QmojTopicTag> getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(List<QmojTopicTag> topicTags) {
        this.topicTags = topicTags;
    }

    public QmojTitleAndContent() {
    }

    public QmojTitleAndContent(String title, String content) {
        this.title = title;
        Content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}