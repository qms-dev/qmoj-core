package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.entity.QmojQuestionSummary;
import team.akina.qmoj.exception.DataNotFindException;
import team.akina.qmoj.service.QmojQuestionService;

import java.util.List;

/**
 * 操作题目相关接口
 */
@Slf4j
@RestController
public class QuestionController {

    @Autowired
    private QmojQuestionService qmojQuestionService;

    /**
     * 获取题目内容
     *
     * @param id 题目ID
     * @return 题目详情
     */
    @RequestMapping("/questions")
    public Response getQuestionContent(@RequestParam("id") int id) {
        QmojQuestionDTO question = qmojQuestionService.getQuestionById(id);
        if (question == null) {
            throw new DataNotFindException("没有找到对应题目", id);
        }
        return Response.success(question);
    }

    /**
     * 获取所有题目摘要列表
     * todo 虽然题目不多，但是这里访问频率较高且涉及全表扫描，后续引入redis协助数据持久化
     * @return 所有题目摘要列表
     */
    @RequestMapping("/questionset")
    public Response getQuestionSet()
    {
        List<QmojQuestionSummary> questionSet = qmojQuestionService.getQuestionSet();
        if (questionSet == null) {
            throw new DataNotFindException("没有获取到任何题目");
        }
        return Response.success(questionSet);
    }
}
