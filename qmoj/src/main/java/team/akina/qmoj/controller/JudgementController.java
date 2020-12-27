package team.akina.qmoj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.dto.QmojJudgementDTO;
import team.akina.qmoj.service.QmojJudgementService;

@RestController
public class JudgementController {

    @Autowired
    QmojJudgementService qmojJudgementService;

    /**
     * 获取题目内容
     *
     * @param id 题目ID
     * @return 题目详情
     */
    @RequestMapping("/judgement")
    public Response getQuestionContent(@RequestParam("id") String id) {
        QmojJudgementDTO judgementResult = qmojJudgementService.queryJudgementResult(id);
        return Response.success(judgementResult);
    }

}
