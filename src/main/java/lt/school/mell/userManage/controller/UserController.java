package lt.school.mell.userManage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.UsersStateEnum;
import lt.school.mell.userManage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    @PostMapping("/open/register")
    @ApiOperation("用户注册")
//    @ApiImplicitParam(name = "users", value = "用户实体", dataType = "Users")
    public RespBean<Users> register(Users users) {
        if (users != null) {
            return RespBean.result(usersService.saveOrUpdate(users));
        } else {

            return RespBean.result(UsersStateEnum.OPERATION_FAILURE());
        }
    }

    @PostMapping("/open/isExists")
    @ApiOperation(value = "根据相关条件查询用户", notes = "需要什么唯一，只需要设置条件即可")
    public RespBean<Users> isExistsUsers(Users users) {
        Users one = usersService.findOne(users);
        if (one != null) {
            return RespBean.success("用户存在");
        } else {
            return RespBean.failure("用户不存在");
        }
    }


    @PostMapping("/index")
    @ApiOperation("登录后进行初始化")
    @ApiResponse(code = 200,response = Users.class,message = "用户信息")
    public RespBean login(HttpServletRequest request){
        String username= (String) request.getAttribute("username");
        return RespBean.result(usersService.init(username));
    }



}
