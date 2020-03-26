package lt.school.mell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@Controller
public class test {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello controller";
    }
}
