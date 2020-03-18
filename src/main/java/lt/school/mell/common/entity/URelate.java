package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (URelate)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:46
 */
@ApiModel
@Data
public class URelate implements Serializable {
    private static final long serialVersionUID = 978135380072194910L;
    private String id;

    @ApiModelProperty("关联用户1")
    private String userId1;

    @ApiModelProperty("关联用户2")
    private String userId2;

    @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
    private String delFlag;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
