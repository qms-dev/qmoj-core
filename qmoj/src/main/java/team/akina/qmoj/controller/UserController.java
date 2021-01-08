package team.akina.qmoj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.entity.QmojUserKey;
import team.akina.qmoj.param.user.*;
import team.akina.qmoj.utils.EmailUtils;
import team.akina.qmoj.utils.EncryptionUtils;
import team.akina.qmoj.entity.QmojUser;
import team.akina.qmoj.service.QmojUserService;
import team.akina.qmoj.utils.JwtUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QmojUserService qmojUserService;

    @Autowired
    private EmailUtils emailUtils;

     @Autowired
     private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/register")
    public Response Register(@RequestBody RegisterParam registerParam){


        if(redisTemplate.opsForValue().get(registerParam.getUser_email()) != null) {
            return Response.fail("用户已注册，请在邮箱激活！");
        }

        QmojUserKey key = new QmojUserKey();
        key.setEmail(registerParam.getUser_email());
        QmojUser userInfo = qmojUserService.GetUserByKey(key);

        if(userInfo != null){
            return Response.fail("用户邮箱已经存在！重复注册。");
        }else {
            userInfo = new QmojUser();
        }

        userInfo.setUserCode(UUID.randomUUID().toString().replace("-", ""));
        userInfo.setPassword(EncryptionUtils.MD5Lower(registerParam.getUser_password()));
        userInfo.setInfo(registerParam.getUser_info());
        userInfo.setName(registerParam.getUser_name());
        userInfo.setEmail(registerParam.getUser_email());
        userInfo.setUpdateTime(new Date());
        userInfo.setRole("USER");
        userInfo.setIsActivated((byte)0);

        String code = UUID.randomUUID().toString().replace("-", "");
        String checkUrl = "http://localhost:8080/user/check?user_code="+code;
        emailUtils.sendHtmlMail("a1756095583@163.com",checkUrl);

        String userJosn = userInfo.toString();
        redisTemplate.opsForValue().set(code, userJosn, 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(userInfo.getEmail(), "0", 1, TimeUnit.MINUTES);

        RegisterReturn registerReturn = new RegisterReturn();
        registerReturn.setExpired_time(60*60*1000);
        return Response.success(registerReturn);
    }


    @RequestMapping("/login")
    public Response Login(@RequestBody LoginParam loginParam){

        QmojUserKey key = new QmojUserKey();
        key.setEmail(loginParam.getUser_email());
        QmojUser user = qmojUserService.GetUserByKey(key);

        loginParam.setUser_password(EncryptionUtils.MD5Lower(loginParam.getUser_password()));

        if(user == null || !user.getPassword().equals(loginParam.getUser_password())){
            return Response.fail("用户邮箱或密码错误！");
        }else if(user.getIsActivated() == 0){
            return Response.fail("用户还未在邮箱确认注册！");
        }
        Map<String, Object> body = new HashMap<>();
        body.put("user_id", user.getUserId());
        body.put("user_name", user.getName());
        body.put("user_info", user.getInfo());
        body.put("user_email", user.getEmail());
        String token = JwtUtils.createToken(body);

        LoginReturn loginReturn = new LoginReturn();
        loginReturn.setToken(token);
        loginReturn.setExpired_time(JwtUtils.TOKEN_EXPIRE_MILLIS);
        return Response.success(loginReturn);
    }


    @RequestMapping("/check")
    public Response CheckRegister(@RequestParam("user_code") String code){
        String userJson = redisTemplate.opsForValue().get(code);

        if(userJson == null){
            return Response.fail("消息过期！");
        }
        ObjectMapper om= new ObjectMapper();
        QmojUser user = null;
        try {
            user = om.readValue(userJson, QmojUser.class);
        } catch (JsonProcessingException e) {
             e.printStackTrace();
        }

        if(user.getIsActivated() == (byte)1){
            return Response.success("用户已注册！");
        }
        user.setIsActivated((byte)1);

        if(qmojUserService.InsertUserInfo(user)> 0){
            return Response.success("用户注册成功！");
        }

        return Response.fail("用户注册失败");
    }

    @RequestMapping("/edit")
    public Response EditUser(@RequestBody EditUserParam param, @RequestHeader(name = "Authorization") String authCode){

        Map<String, Object>baseInfo = JwtUtils.parseToken(authCode.split(" ")[1]);

        QmojUser userInfo = new QmojUser();

        userInfo.setName(param.getUser_name());
        userInfo.setInfo(param.getUser_info());
        userInfo.setPassword(EncryptionUtils.MD5Lower(param.getUser_password()));
        userInfo.setUpdateTime(new Date());

        userInfo.setUserId((Long.valueOf(baseInfo.get("user_id").toString())));
        userInfo.setEmail(baseInfo.get("user_email").toString());

        qmojUserService.UpdateUserInfo(userInfo);
        return Response.success("用户信心修改成功！");
    }
}
