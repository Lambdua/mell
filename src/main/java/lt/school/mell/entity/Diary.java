package lt.school.mell.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Diary)实体类
 *
 * @author makejava
 * @since 2020-03-03 10:53:53
 */
@Data
public class Diary implements Serializable {
    private static final long serialVersionUID = 622634957495541563L;
    
    private String id;
    /**
    * 用户id
    */
    private String userId;
    /**
    * 日记内容
    */
    private String diaryContent;
    /**
    * 公开等级，0：自己可见 1：彼此可见 2：鱼塘可见 3：全部可见 
    */
    private Integer openLevel;
    /**
    * 星星数/点赞数
    */
    private Integer starCount;
    /**
    * 日记标题
    */
    private String title;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * 创建日期
    */
    private Date createDate;

}