package lt.school.mell.userManage.controller;

import lt.school.mell.utils.ajaxBean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@RestController
public class test {

    @PostMapping("/hello")
    public String hello() {
        return "hello controller";
    }

    @GetMapping("/")
    public String index(){
        return  "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/dologin")
    public String dologin() {
        return "dologin";
    }

    @PostMapping("/userlogin")
    public String postLogin() {
        return "postLogin";
    }


    @GetMapping("/getJsonStr")
    public RespBean getJsonStr(){
        Map<String,String> result=new HashMap<>();
        result.put("name","zhangsan");
        result.put("age","123");
        return RespBean.ok("getJson",result);
    }

}
