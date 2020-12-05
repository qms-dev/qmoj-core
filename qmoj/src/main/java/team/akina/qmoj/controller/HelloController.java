package team.akina.qmoj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.service.QmojLanguageService;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private QmojLanguageService qmojLanguageService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
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
