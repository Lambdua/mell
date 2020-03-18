package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SurveyName)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:46
 */
@ApiModel
@Data
public class SurveyName implements Serializable {
    private static final long serialVersionUID = -64669863809573510L;
    private String id;

    @ApiModelProperty("问卷名称")
    private String title;

    @ApiModelProperty("问卷描述")
    private String description;

    @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
    private String delFlag;

    @ApiModelProperty("问卷类型 类型待定")
    private String type;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
