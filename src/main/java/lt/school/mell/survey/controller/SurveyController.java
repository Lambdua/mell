package lt.school.mell.survey.controller;

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
    public RespBean getSurveyList(@RequestParam String type) {
        return RespBean.result(surveyService.getSurveyList(type));
    }

    @GetMapping("getUserSurveyResult")
    public RespBean getUserSurveyResult(@RequestParam String userId) {
        return RespBean.result(surveyService.getUserSurveyResult(userId));
    }


    @GetMapping("getSurveyDetail")
    public RespBean getSurveyDetail(@RequestParam String surveyNameId) {
        return RespBean.result(surveyService.getSurveyDetail(surveyNameId));
    }


    @PostMapping("saveUserSurveyResults")
    public RespBean saveUserSurveyResults(@RequestBody List<UserServeyCollect> userSurveyCollects) {
        return RespBean.result(surveyService.saveUserSurveyResults(userSurveyCollects));
    }

    @GetMapping("getSurveyReesultBySurvey")
    public RespBean getSurveyReesultBySurvey(String surveyNameId){
        return RespBean.result(surveyService.getSurveyReesultBySurvey(surveyNameId));
    }


    @GetMapping("getPsychologicalKnowledgeList")
    public RespBean getPsychologicalKnowledgeList(){
        return RespBean.result(psylogicKnowledgeService.getPsychologicalKnowledgeList());
    }

}
