package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.service.QmojLanguageService;
import team.akina.qmoj.utils.LeetCodeHelper;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private QmojLanguageService qmojLanguageService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    private LeetCodeHelper leetCodeHelper;

    @Autowired
    public HelloController(LeetCodeHelper leetCodeHelper) {
        this.leetCodeHelper = leetCodeHelper;
    }

    /**
    * 演示题目获取，实际使用时，请在Service层进行与LeetCode的相关操作
    * @param slug LeetCode题目的Slug
    * @return 题目详情
    */
    @RequestMapping("/helloqmoj/{slug}")
    public String hello(@PathVariable String slug) throws Exception {
        return leetCodeHelper.getProblemDetailBySlug(slug);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/getLanguageById")
    public Response getLanuageById(Long id) {
        log.info("------------------");
        return Response.success(qmojLanguageService.findQmojLanguageById(id));
    }
}
