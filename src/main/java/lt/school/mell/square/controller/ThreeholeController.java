package lt.school.mell.square.controller;

import io.swagger.annotations.ApiOperation;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.entity.Threehole;
import lt.school.mell.square.service.ThreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liangtao
 * @Date 2020/4/2
 **/
@RestController
@RequestMapping("" +
        "/threehole")
public class ThreeholeController {
    @Autowired
    ThreeholeService threeholeService;

    @GetMapping("getTypes")
    @ApiOperation("获取树洞的类型信息")
    public RespBean getTypes() {
        return RespBean.result(threeholeService.getTypes());
    }


    @GetMapping("getThreeholeByType")
    @ApiOperation("获取树洞对应类型的分页数据")
    public RespBean getThreeholeByType(
            @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String type) {
        return RespBean.result(threeholeService.getThreeholeByType(pageNum, pageSize, type));
    }


    @PostMapping("saveThreehole")
    @ApiOperation("存储树洞消息")
    public RespBean saveThreehole(@RequestBody Threehole threehole) {
        return RespBean.result(threeholeService.saveThreehole(threehole));
    }
}
