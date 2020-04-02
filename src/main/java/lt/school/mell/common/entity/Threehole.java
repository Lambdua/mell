package lt.school.mell.common.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Threehole)实体类
 *
 * @author liangtao
 * @since 2020-04-02 12:19:27
 */
@ApiModel
@Data
public class Threehole implements Serializable {
    private static final long serialVersionUID = 309896092117364004L;
        @ApiModelProperty("主键")
        private String id;
    
        @ApiModelProperty("用户表id")
        private String userId;
    
        @ApiModelProperty("内容类型")
        private String type;
    
        @ApiModelProperty("内容")
        private String content;
    
        @ApiModelProperty("相应得分")
        private Integer score;
    
        @ApiModelProperty("删除标志")
        private String delFlag;
    
        @ApiModelProperty("创建日期")
        private Date createDate;
    
        @ApiModelProperty("更新日期")
        private Date updateDate;
    
}