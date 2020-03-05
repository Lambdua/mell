package lt.school.mell.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (DiaryComment)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class DiaryComment implements Serializable {
    private static final long serialVersionUID = -80727559702058114L;
    
    private String id;
    /**
    * 评论内容
    */
    private String commentContent;
    /**
    * 日记id
    */
    private String diaryId;
    /**
    * 排序
    */
    private Integer sort;
    /**
    * 父类id
    */
    private String parentId;
    /**
    * 用户id
    */
    private String userId;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * 创建日期
    */
    private Date createDate;




}