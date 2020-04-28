package lt.school.mell.square.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.square.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liangtao
 * @Date 2020/3/20
 **/

@RestController
@Api(tags = "广场模块")
@RequestMapping("/square")
public class SquareController {
    //    @Autowired
//    DiaryService diaryService;
    @Autowired
    SquareService squareService;


    @GetMapping("/getSquare")
    @ApiOperation("获取广场日记,和评论")
    public RespBean getSquare(int pageNum, int pageSize) {
        return RespBean.result(squareService.getSquares(pageNum, pageSize));
    }


    @PostMapping("/comment")
    @ApiOperation("评论")
    public RespBean comment(
            @RequestParam String userId,
            @RequestParam String comment,
            @RequestParam String diaryId) {
        return RespBean.result(squareService.saveComment(userId, comment, diaryId));

    }


}
