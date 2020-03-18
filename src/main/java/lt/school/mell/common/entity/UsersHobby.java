package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (UsersHobby)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:47
 */
@ApiModel
@Data
public class UsersHobby implements Serializable {
    private static final long serialVersionUID = -16004027017526765L;
    private String id;

    private String usersId;

    @ApiModelProperty("爱好对应id")
    private String hobbyId;

    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    private String delFlag;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
