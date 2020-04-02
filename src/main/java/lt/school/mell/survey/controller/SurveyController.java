package lt.school.mell.survey.controller;

import io.swagger.annotations.ApiOperation;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.common.entity.UserServeyCollect;
import lt.school.mell.survey.service.PsylogicKnowledgeService;
import lt.school.mell.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/31
 **/
@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;
    @Autowired
    PsylogicKnowledgeService psylogicKnowledgeService;

    @GetMapping("getSurveyList")
    @ApiOperation(("获取测试问卷列表"))
    public RespBean getSurveyList(@RequestParam String type) {
        return RespBean.result(surveyService.getSurveyList(type));
    }

    @GetMapping("getUserSurveyResult")
    @ApiOperation("获取用户的测试结果")
    public RespBean getUserSurveyResult(@RequestParam String userId) {
        return RespBean.result(surveyService.getUserSurveyResult(userId));
    }


    @GetMapping("getSurveyDetail")
    @ApiOperation("获取该问卷的所有内容")
    public RespBean getSurveyDetail(@RequestParam String surveyNameId) {
        return RespBean.result(surveyService.getSurveyDetail(surveyNameId));
    }


    @PostMapping("saveUserSurveyResults")
    @ApiOperation("保存用户测试结果")
    public RespBean saveUserSurveyResults(@RequestBody List<UserServeyCollect> userSurveyCollects) {
        return RespBean.result(surveyService.saveUserSurveyResults(userSurveyCollects));
    }

    @GetMapping("getSurveyReesultBySurvey")
    @ApiOperation("获取对应问卷测试的结果集合")
    public RespBean getSurveyReesultBySurvey(String surveyNameId){
        return RespBean.result(surveyService.getSurveyReesultBySurvey(surveyNameId));
    }


    @GetMapping("getPsychologicalKnowledgeList")
    @ApiOperation("获取心理知识集和")
    public RespBean getPsychologicalKnowledgeList(){
        return RespBean.result(psylogicKnowledgeService.getPsychologicalKnowledgeList());
    }

}
