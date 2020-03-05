package lt.school.mell.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (URelate)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class URelate implements Serializable {
    private static final long serialVersionUID = 529895866763357412L;
    
    private String id;
    /**
    * 关联用户1
    */
    private String uid1;
    /**
    * 关联用户2
    */
    private String uid2;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;
    /**
    * 创建日期
    */
    private Date createDate;
    /**
    * 修改日期
    */
    private Date updateDate;




}