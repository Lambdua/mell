package lt.school.mell.survey.entity;

import lombok.Data;
import lt.school.mell.common.entity.SurveyResult;
import lt.school.mell.common.entity.UserServeyCollect;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/31
 * 返回用户的测试问卷结果
 **/
@Data
public class SurveyResultByUser {
    String surveyName;
    List<UserServeyCollect> userServeyCollectList;
    List<SurveyResult> SurveyResultList;

    public SurveyResultByUser(String surveyName, List<UserServeyCollect> userServeyCollectList, List<SurveyResult> surveyResultList) {
        this.surveyName = surveyName;
        this.userServeyCollectList = userServeyCollectList;
        SurveyResultList = surveyResultList;
    }
}
