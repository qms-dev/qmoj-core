package team.akina.qmoj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.exception.DataNotFindException;
import team.akina.qmoj.pojo.QmojAnswer;
import team.akina.qmoj.service.QmojQuestionService;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    private QmojQuestionService qmojQuestionService;


    /**
     * 提交题解 todo 还需完成根据qmoj题目id查询到leetcode题目id，然后根据id提交题目(参照LeetCodeHelperTest)
     *
     * @param answer
     * @return
     */
    @RequestMapping("/answers/submit")
    public Response getQuestionContent(@RequestBody QmojAnswer answer) {
        throw new NotImplementedException();
    }

}
