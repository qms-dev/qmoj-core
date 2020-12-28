package team.akina.qmoj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;
import team.akina.qmoj.mapper.QmojQuestionMapper;
import team.akina.qmoj.pojo.QmojStatStatusPairs;
import team.akina.qmoj.pojo.QmojTitleAndContent;
import team.akina.qmoj.pojo.QmojTopicTag;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.dto.mapper.QmojQuestionDTOMapper;
import team.akina.qmoj.entity.QmojQuestionSummary;
import team.akina.qmoj.mapper.QmojQuestionSummaryMapper;
import team.akina.qmoj.service.QmojQuestionService;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QmojQuestionServiceImpl implements QmojQuestionService {


    @Autowired
    private QmojQuestionMapper qmojQuestionMapper;

    @Autowired
    private LeetCodeHelper leetCodeHelper;


    @Autowired
    private QmojQuestionSummaryMapper qmojQuestionSummaryMapper;

    /**
     * todo 这里获取到题目之后，需要添加当前数据库没有的题目
     */
    @Override
    public void updateQuestionsFromLeetCode()  {
        List<QmojStatStatusPairs> qmojStatStatusPairs = leetCodeHelper.getQuestionsList();
        for(int i = 0 ;i<qmojStatStatusPairs.size();i++)
        {
            QmojStatStatusPairs qmojStatStatusPairs1 = qmojStatStatusPairs.get(i);
            String responseJson = leetCodeHelper.getQuestionDetailBySlug(qmojStatStatusPairs1.getQuestion__title_slug());

            JSONObject dataJson = JSON.parseObject(JSON.parseObject(responseJson).get("data").toString()) ;

            JSONObject questionJson = JSON.parseObject(dataJson.get("question").toString());

            QmojTitleAndContent qmojTitleAndContent = new QmojTitleAndContent();

            if(questionJson.get("translatedTitle")!=null)
            {
                qmojTitleAndContent.setTitle(questionJson.get("translatedTitle").toString());
            }
            else if(questionJson.get("title")!=null)
            {
                qmojTitleAndContent.setTitle(questionJson.get("title").toString());
            }
            else
            {
                qmojTitleAndContent.setTitle("");
            }

            if(questionJson.get("translatedContent")!=null)
            {
                qmojTitleAndContent.setContent(questionJson.get("translatedContent").toString());
            }
            else if(questionJson.get("content")!=null)
            {
                qmojTitleAndContent.setContent(questionJson.get("content").toString());
            }
            else
            {
                qmojTitleAndContent.setContent("");
            }


            JSONArray topicTags = questionJson.getJSONArray("topicTags");
            List<QmojTopicTag>qmojTopicTags= new ArrayList<QmojTopicTag>();
            for(int j = 0 ;j<topicTags.size();j++)
            {
                QmojTopicTag qmojTopicTag = new QmojTopicTag();
                qmojTopicTag.setTopictag_name(topicTags.getJSONObject(j).get("name").toString());
                qmojTopicTag.setTopictag_slug(topicTags.getJSONObject(j).get("slug").toString());
                if(topicTags.getJSONObject(j).get("translatedName")!=null)
                {
                    qmojTopicTag.setTopictag_translatedName(topicTags.getJSONObject(j).get("translatedName").toString());
                }
                else
                {
                    qmojTopicTag.setTopictag_translatedName("");
                }
                qmojTopicTags.add(qmojTopicTag);
            }
            qmojTitleAndContent.setTopicTags(qmojTopicTags);

            if(qmojQuestionMapper.selectByPrimaryKey(qmojStatStatusPairs1.getQuestion_id())==null)
            {
                QmojQuestionWithBLOBs qmojQuestionWithBLOBs = new QmojQuestionWithBLOBs();
                qmojQuestionWithBLOBs.setId(qmojStatStatusPairs1.getQuestion_id());
                qmojQuestionWithBLOBs.setQuestionId(i++);
                qmojQuestionWithBLOBs.setDifficulty((byte)qmojStatStatusPairs1.getLevel());
                qmojQuestionWithBLOBs.setCreateTime(new Date());
                qmojQuestionWithBLOBs.setModifyTime(new Date());
                qmojQuestionWithBLOBs.setPlatform((byte)0);
                qmojQuestionWithBLOBs.setQuestionSlug(qmojStatStatusPairs1.getQuestion__title_slug());
                qmojQuestionWithBLOBs.setTitle(qmojTitleAndContent.getTitle());
                qmojQuestionWithBLOBs.setContent(qmojTitleAndContent.getContent());
                qmojQuestionWithBLOBs.setTopicTags(JSON.toJSONString(qmojTopicTags));
                qmojQuestionWithBLOBs.setLangToValidPlayground(Constants.DEFAULT_PROGRAMMING_LANGUAGE);
                qmojQuestionMapper.insert(qmojQuestionWithBLOBs);
            }
        }
    }

    /**
     * 根据题目id获取题目内容
     *
     * @param id 题目id
     * @return
     */
    @Override
    public QmojQuestionDTO getQuestionById(long id) {
        return QmojQuestionDTOMapper.INSTANCE.questionToQuestionDTO(qmojQuestionMapper.selectByPrimaryKey(id));
    }

    /**
     * 获取所有题目摘要列表
     *
     * @return 所有题目摘要列表
     */
    @Override
    public List<QmojQuestionSummary> getQuestionSet() {
        return qmojQuestionSummaryMapper.getAll();
    }
}
