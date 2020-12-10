package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.service.QmojLanguageService;
import team.akina.qmoj.utils.LeetCodeHelper;

/**
 * 操作题目相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    @Autowired
    private QmojLanguageService qmojLanguageService;

    @Autowired
    private LeetCodeHelper leetCodeHelper;

    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    /**
     * Todo 获取题目内容接口，还需完成dto实体及从数据库获取题目
     *
     * @param id 题目ID
     * @return 题目详情
     */
    @RequestMapping
    public Response getProblemContent(@RequestParam("id") int id) {
        return Response.success("题目:" + id);
    }
}
