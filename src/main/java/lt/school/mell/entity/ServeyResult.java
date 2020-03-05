package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ServeyResult)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class ServeyResult implements Serializable {
    private static final long serialVersionUID = 824934180116506085L;
    
    private String id;
    /**
    * 测试结果的类型
    */
    private String type;
    /**
    * 结果的简短描述
    */
    private String description;
    /**
    * 结果的详细描述
    */
    private String reachDescText;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * surveyName表关联id
    */
    private String surveyNameId;




}