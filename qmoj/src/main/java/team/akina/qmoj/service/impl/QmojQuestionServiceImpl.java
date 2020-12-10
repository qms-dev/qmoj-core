package team.akina.qmoj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.akina.qmoj.entity.QmojLanguage;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.mapper.QmojLanguageMapper;
import team.akina.qmoj.service.QmojLanguageService;
import team.akina.qmoj.service.QmojQuestionService;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.util.List;

@Service
public class QmojQuestionServiceImpl implements QmojQuestionService {

    @Autowired
    private LeetCodeHelper leetCodeHelper;

    /**
     * todo 这里获取到题目之后，需要添加当前数据库没有的题目
     */
    @Override
    public void updateProblemsFromLeetCode() {
        List<QmojQuestion> problemListWithDetail = leetCodeHelper.getProblemListWithDetail();

    }
}
