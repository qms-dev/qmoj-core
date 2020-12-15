package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.exception.InvalidInputException;
import team.akina.qmoj.service.QmojLanguageService;
import team.akina.qmoj.utils.LeetCodeHelper;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private QmojLanguageService qmojLanguageService;

    @Autowired
    private LeetCodeHelper leetCodeHelper;

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * 演示题目获取，实际使用时，请在Service层进行与LeetCode的相关操作
     *
     * @param slug LeetCode题目的Slug
     * @return 题目详情
     */
    @RequestMapping("/helloqmoj/{slug}")
    public Response hello(@PathVariable String slug) {
        if (slug.equals("1")) {
            throw new InvalidInputException("请输入正确的题目标识", slug);
        }
        return Response.success(leetCodeHelper.getQuestionDetailBySlug(slug));
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/getLanguageById")
    public Response getLanuageById(Long id) {
        logger.info("------------------");
        return Response.success(qmojLanguageService.findQmojLanguageById(id));
    }
}
