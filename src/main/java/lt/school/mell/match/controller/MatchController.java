package lt.school.mell.match.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangtao
 * @Date 2020/3/18
 **/
@RestController
@RequestMapping("/match/test")
@Api(tags = "匹配模块")
public class MatchController {
    @Autowired
    MatchService matchService;

    @GetMapping("/startMatch")
    @ApiOperation("开启匹配")
    public RespBean startMatch(@ApiParam("id") String userId) {
        BaseEnum result = matchService.startMatch(userId);
        return RespBean.result(result);
    }


    @GetMapping("/wantMatch")
    @ApiOperation("特定人匹配")
    public RespBean wantMatch(@ApiParam("id") String userId, @ApiParam("对方的用户名") String username) {
        return RespBean.result(matchService.wantMatch(userId, username));
    }


    @GetMapping("/getMatches")
    @ApiOperation("获取请求匹配该用户的用户名单")
    public RespBean getMatches(String userId){
        return RespBean.result(matchService.findMatches(userId));
    }


    @GetMapping("/allowMatch")
    @ApiOperation("同意")
    public RespBean allowMatch(String userId,@ApiParam("对方用户Id") String otherId){
        return RespBean.result(matchService.allowMatch(userId,otherId));
    }


    @GetMapping("/denyMatch")
    @ApiOperation("拒绝该匹配")
    public RespBean denyMatch(String userId,@ApiParam("对方用户Id") String otherId){
        return RespBean.result(matchService.denyMatch(userId,otherId));
    }

}
