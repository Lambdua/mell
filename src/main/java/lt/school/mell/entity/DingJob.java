package lt.school.mell.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (DingJob)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class DingJob implements Serializable {
    private static final long serialVersionUID = -20966432538139429L;
    
    private String id;
    /**
    * 推送时间
    */
    private Date submitDate;
    /**
    * 创建日期
    */
    private Date createDate;
    /**
    * 推送方式 0 app内 1短信 2
    */
    private String type;
    /**
    * 推送内容
    */
    private String content;
    /**
    * 推送id
    */
    private String subUserId;
    /**
    * 接收id
    */
    private String receiveUserId;
    /**
    * 逻辑删除标志 0假1真
    */
    private String delFlag;




}