package lt.school.mell.common.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ThreeholeType)实体类
 *
 * @author liangtao
 * @since 2020-04-02 12:19:27
 */
@ApiModel
@Data
public class ThreeholeType implements Serializable {
    private static final long serialVersionUID = -17973658730694396L;
        @ApiModelProperty("主键")
        private String id;
    
        @ApiModelProperty("树洞的发表类型")
        private String type;
    
        @ApiModelProperty("删除标志")
        private String delFlag;
    
        @ApiModelProperty("创建日期")
        private Date createDate;
    
        @ApiModelProperty("更新日期")
        private Date updateDate;
    
}