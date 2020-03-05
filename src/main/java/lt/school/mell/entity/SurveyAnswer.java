package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SurveyAnswer)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class SurveyAnswer implements Serializable {
    private static final long serialVersionUID = 226288169414078899L;
    
    private String id;
    /**
    * question表id
    */
    private String questionId;
    /**
    * 选项字符串
    */
    private String choicetext;
    /**
    * 选项排序
    */
    private Integer sort;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * surveyName表关联id
    */
    private String surveyNameId;



}