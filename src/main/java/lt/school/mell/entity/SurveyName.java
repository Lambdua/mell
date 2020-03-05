package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SurveyName)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class SurveyName implements Serializable {
    private static final long serialVersionUID = 185637306668083686L;
    
    private String id;
    /**
    * 问卷名称
    */
    private String title;
    /**
    * 问卷描述
    */
    private String description;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * 问卷类型 类型待定
    */
    private String type;




}