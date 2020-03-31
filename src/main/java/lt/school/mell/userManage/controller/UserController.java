package lt.school.mell.userManage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.UsersStateEnum;
import lt.school.mell.userManage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UsersService usersService;

/*
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
*/


    @PostMapping("/open/register")
    @ApiOperation("用户注册")
    public RespBean<Users> register(Users users) {
        if (users != null) {
            return RespBean.result(usersService.saveOrUpdate(users));
        } else {
            return RespBean.result(UsersStateEnum.OPERATION_FAILURE());
        }
    }

    @PostMapping("/open/isExists")
    @ApiOperation(value = "根据相关条件查询用户", notes = "需要什么唯一，只需要设置条件即可")
    @ApiResponse(code = 200, response = Users.class, message = "用户信息")
    public RespBean<Users> isExistsUsers(Users users, HttpServletRequest request) {
        Users one = usersService.findOne(users);
        if (one != null) {
            return RespBean.success("用户存在");
        } else {
            return RespBean.failure("用户不存在", 200);
        }
    }


    @PostMapping("/index")
    @ApiOperation("登录后进行初始化")
    @ApiResponses({
            @ApiResponse(code = 200, response = Users.class, message = "用户信息"),
            @ApiResponse(code = 200, response = String.class, message = "token")})
    public RespBean login(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Users users = usersService.init(username);
        Map<String, Object> result = new HashMap<>();
        result.put("token", users.getId());
        result.put("users", users);
        return RespBean.success(result);
    }


    @GetMapping("getInfo")
    @ApiOperation("获取用户信息")
    @ApiResponse(code = 200, response = Users.class, message = "用户信息")
    public RespBean getInfo(String token) {
        BaseEnum usersStateEnum = usersService.get(token);
        return RespBean.result(usersStateEnum);
    }


    @PostMapping("updateUsers")
    @ApiOperation("用户信息的更新,需要用户名和id")
    public RespBean updateUsers(@RequestBody Users users){
        BaseEnum baseEnum = usersService.saveOrUpdate(users);
        return RespBean.result(baseEnum);
    }
}
