package lt.school.mell.userManage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.entity.Hobby;
import lt.school.mell.common.entity.Users;
import lt.school.mell.userManage.service.HobbyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/16
 **/
@RestController
@RequestMapping("/user/hobby/test")
@Api(tags = "用户管理")
public class HobbyController {

    @Autowired
    HobbyService hobbyService;


    @GetMapping("/getHobbys")
    @ApiOperation("获取爱好信息表")
    public RespBean<List<Hobby>> getHobbys() {
        return RespBean.result(hobbyService.findList(null));
    }


    @PostMapping("/submitHobbys")
    @ApiOperation("用户爱好信息的存储")
    public RespBean submitHobbys(
            @ApiParam(value = "用户", required = true)
            @RequestParam Users users,
            @ApiParam("用户所选列表") List<Hobby> hobbyList) {

        if (users == null || StringUtils.isBlank(users.getId()) || hobbyList == null) {
            return RespBean.result(BaseEnum.GET_PARAM_FAILURE());
        }
        return RespBean.result(hobbyService.saveUserHobbys(users, hobbyList));
    }



}
