package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.exception.DataNotFindException;
import team.akina.qmoj.service.QmojQuestionService;

/**
 * 操作题目相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api")
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
}
