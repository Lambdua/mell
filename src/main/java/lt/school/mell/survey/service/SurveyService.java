package lt.school.mell.survey.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lt.school.mell.common.entity.*;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.SurveyEnum;
import lt.school.mell.common.mapper.*;
import lt.school.mell.survey.entity.SurveyResultByUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author liangtao
 * @Date 2020/3/31
 **/
@Service
public class SurveyService {
    @Autowired
    SurveyNameMapper surveyNameMapper;
    @Autowired
    SurveyQuestionMapper questionMapper;
    @Autowired
    SurveyAnswerMapper answerMapper;
    @Autowired
    SurveyResultMapper resultMapper;
    @Autowired
    UserServeyCollectMapper userServeyCollectMapper;


    public BaseEnum getUserSurveyResult(String userId) {
        List<SurveyResultByUser> resultList = new ArrayList<>();
        List<String> groups = userServeyCollectMapper.selectGroups(userId);

        if (groups.size() == 0) {
            return SurveyEnum.NO_HAVE();
        }

        List<UserServeyCollect> userList = null;
        List<SurveyResult> surveyResultList = null;
        SurveyName surveyName=null;
        for (String group : groups) {
            userList = userServeyCollectMapper.selectList(Wrappers.lambdaQuery(UserServeyCollect.class)
                    .eq(UserServeyCollect::getUserId, userId)
                    .eq(UserServeyCollect::getGroupId, group)
                    .orderByDesc(UserServeyCollect::getResultScore)
                    .orderByDesc(UserServeyCollect::getCreateDate));
            String surveyId = userList.get(0).getSurveyId();
             surveyName= surveyNameMapper.selectById(surveyId);
            surveyResultList = resultMapper.selectList(Wrappers.lambdaQuery(SurveyResult.class).eq(SurveyResult::getSurveyNameId, surveyId).orderByDesc(SurveyResult::getCreateDate));
            resultList.add(new SurveyResultByUser(surveyName.getTitle(), surveyName.getType(),userList, surveyResultList));
        }
        return BaseEnum.GET_SUCCESS(resultList);
    }

    public BaseEnum getSurveyList() {
        return BaseEnum.GET_SUCCESS(surveyNameMapper.selectList(null));
/*
        return BaseEnum.GET_SUCCESS(surveyNameMapper.selectList(Wrappers.lambdaQuery(SurveyName.class)
                .eq(SurveyName::getType, type)));
*/
    }


    public BaseEnum getSurveyDetail(String surveyNameId) {
        SurveyName surveyName = surveyNameMapper.selectById(surveyNameId);
        if (surveyName == null) {
            return SurveyEnum.NO_HAVE();
        }

        List<SurveyQuestion> questionList = questionMapper.selectList(Wrappers.lambdaQuery(SurveyQuestion.class)
                .eq(SurveyQuestion::getSurveyNameId, surveyNameId));

        surveyName.setSurveyQuestionList(questionList);

        for (SurveyQuestion question : questionList) {
            List<SurveyAnswer> answerList = answerMapper.selectList(Wrappers.lambdaQuery(SurveyAnswer.class)
                    .eq(SurveyAnswer::getQuestionId, question.getId()));
            question.setSurveyAnswerList(answerList);
        }
        return BaseEnum.GET_SUCCESS(surveyName);
    }

    public BaseEnum getSurveyReesultBySurvey(String surveyNameId) {
        List<SurveyResult> results = resultMapper.selectList(Wrappers.lambdaQuery(SurveyResult.class).eq(SurveyResult::getSurveyNameId, surveyNameId));
        return BaseEnum.GET_SUCCESS(results);
    }


    public BaseEnum saveUserSurveyResults(List<UserServeyCollect> userSurveyCollects) {
        String groupId = UUID.randomUUID().toString().replaceAll("-", "");
        List<SurveyResult> results = resultMapper.selectList(Wrappers.lambdaQuery(SurveyResult.class)
                .eq(SurveyResult::getSurveyNameId, userSurveyCollects.get(0).getSurveyId()));
        userSurveyCollects.forEach(item -> {
            item.setGroupId(groupId);
            userServeyCollectMapper.insert(item);
        });
        return BaseEnum.SAVE_SUCCESS(results);
    }

}
