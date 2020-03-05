package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (UserServeyCollect)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class UserServeyCollect implements Serializable {
    private static final long serialVersionUID = 802611910693984918L;
    
    private String id;
    /**
    * 用户表id
    */
    private String userId;
    /**
    * 测试问卷id
    */
    private String serveyId;
    /**
    * 测试问卷结果id
    */
    private String servueResultId;
    /**
    * 该结果得分值
    */
    private Integer resultScore;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;




}