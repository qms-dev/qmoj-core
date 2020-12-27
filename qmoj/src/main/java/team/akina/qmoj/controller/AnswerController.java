package team.akina.qmoj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.pojo.QmojAnswer;
import team.akina.qmoj.service.QmojAnswerService;
import team.akina.qmoj.service.QmojQuestionService;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    private QmojQuestionService qmojQuestionService;

    @Autowired
    private QmojAnswerService qmojAnswerService;

    /**
     * 提交题解
     *
     * @param answer 题解信息
     * @return
     */
    @RequestMapping("/answers/submit")
    public Response getQuestionContent(@RequestBody QmojAnswer answer) {
        return Response.success(qmojAnswerService.submitAnswer(answer));
    }

}
