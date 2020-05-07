package lt.school.mell.diary.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.entity.Diary;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.DiaryEnum;
import lt.school.mell.diary.service.DiaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liangtao
 * @Date 2020/3/17
 **/
@RestController
@Api(tags = "日记管理")
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;


    @PostMapping("/saveDiary")
    @ApiOperation("日记的新增")
    public RespBean saveDiary(
            @ApiParam("新增日记")
            @RequestBody Diary diary) {
        if (diary == null || diary.getUserId() == null) {
            return RespBean.result(DiaryEnum.GET_PARAM_FAILURE());
        }
        return RespBean.result(diaryService.saveOrUpdate(diary));
    }


/*
    @Autowired
    PsychologicalKnowledgeMapper testMapper;
    @PostMapping("/saveDiary")
    public RespBean saveTest(
            @RequestBody  Diary diary){
        PsychologicalKnowledge psychologicalKnowledge=new PsychologicalKnowledge();
        psychologicalKnowledge.setDelFlag("0");
        psychologicalKnowledge.setTitle(diary.getTitle());
        psychologicalKnowledge.setContent(diary.getDiaryContent());
        testMapper.insert(psychologicalKnowledge);
        return RespBean.result(BaseEnum.SAVE_SUCCESS());
    }
*/

    @DeleteMapping("/delete")
    @ApiOperation("日记的删除")
    public RespBean deleteDiary(String diaryId) {
        if (StringUtils.isBlank(diaryId)) {
            return RespBean.result(DiaryEnum.GET_PARAM_FAILURE());
        }
        return RespBean.result(diaryService.remove(diaryId));
    }


    @PostMapping("/updateDiary")
    @ApiOperation("日记属性的更新")
    public RespBean updateDiary(
            @RequestBody  Diary diary) {
        if (diary == null || diary.getUserId() == null) {
            return RespBean.result(DiaryEnum.GET_PARAM_FAILURE());
        }
        return RespBean.result(diaryService.saveOrUpdate(diary));
    }


    @GetMapping("/getListByself")
    @ApiOperation("获取用户自己的日记")
    public RespBean getList(String userId,
                            @ApiParam("页码") @RequestParam(defaultValue = "1") int pageNum,
                            @ApiParam("每页条数") @RequestParam(defaultValue = "20") int pageSize) {
        if (StringUtils.isBlank(userId)) {
            return RespBean.result(DiaryEnum.GET_PARAM_FAILURE());
        }

        return RespBean.result(diaryService.findList(userId, pageNum, pageSize));
    }


    @GetMapping("/getDiaryByDate")
    @ApiOperation("根据日期获取用户的日记")
    public RespBean getDiaryByDate(
            @ApiParam("创建日期，格式yyyy-MM-DD") String createDate, String userId) {
        return RespBean.result(diaryService.findByDate(createDate, userId));
    }


    @GetMapping("/getDiaryByMonth")
    @ApiOperation("根据月份获取用户的日记")
    public RespBean getDiaryByMonth(String userId,
                                    @ApiParam("月份 格式为 yyyy-MM-DD") String createMonth){
        return RespBean.result(diaryService.findByMonth(userId,createMonth));
    }



    @GetMapping("/getListBySquare")
    @ApiOperation("广场展示日记的获取")
    public RespBean getListBySquare(
            @ApiParam("页码") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam("每页条数") @RequestParam(defaultValue = "20") int pageSize) {

        BaseEnum<Page<Diary>> listBySquare = diaryService.findListBySquare(pageNum, pageSize);
        return RespBean.result(listBySquare);
    }

    @GetMapping("/getListByOtherSide")
    @ApiOperation("获取对方的日记")
    public RespBean getListByOtherSide(
            @ApiParam("用户id") String userId,
            @ApiParam("页码") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam("每页条数") @RequestParam(defaultValue = "10") int pageSize) {

        return RespBean.result(diaryService.findListByOtherSide(userId, pageNum, pageSize));
    }

    @GetMapping("/getListByOtherSideWithDate")
    @ApiOperation("根据日期获取对方的日记")
    public RespBean getListByOtherSideWithDate(String userId,
                                               @ApiParam("月份 格式为 yyyy-MM-dd") String createMonth){
        return  RespBean.result(diaryService.getListByOtherSideWithDate(userId,createMonth));

    }

}
