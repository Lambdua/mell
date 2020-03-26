package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Hobby)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:45
 */
@ApiModel
@Data
public class Hobby implements Serializable {
    private static final long serialVersionUID = 122947333687388847L;
    private String id;

    @ApiModelProperty("爱好名称")
    private String name;

    @ApiModelProperty("级别 1级别最高，依次递减")
    @JsonIgnore
    private Integer hobbyLevel;

    @ApiModelProperty("父类id")
    @JsonIgnore
    private String parentId;

    @ApiModelProperty("爱好描述")
    private String description;

    @ApiModelProperty("对应图标")
    private String img;

    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    @JsonIgnore
    private String delFlag;

    @ApiModelProperty(value = "创建日期",hidden = true)
    @JsonIgnore
    private Date createDate;

    @ApiModelProperty(value = "修改日期" ,hidden = true)
    @JsonIgnore
    private Date updateDate;

}
