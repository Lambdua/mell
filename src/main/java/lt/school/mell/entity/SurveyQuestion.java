package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SurveyQuestion)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class SurveyQuestion implements Serializable {
    private static final long serialVersionUID = 188380592727942302L;
    
    private String id;
    /**
    * 问题名称
    */
    private String question;
    /**
    * 问题的类型 0单选 1多选 2得分
    */
    private String answerType;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * surveyName表关联id
    */
    private String surveyNameId;



}