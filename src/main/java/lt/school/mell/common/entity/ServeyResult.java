package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (ServeyResult)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:45
 */
@ApiModel
@Data
public class ServeyResult implements Serializable {
    private static final long serialVersionUID = -75943548124928697L;
    private String id;

    @ApiModelProperty("测试结果的类型")
    private String type;

    @ApiModelProperty("结果的简短描述")
    private String description;

    @ApiModelProperty("结果的详细描述")
    private String reachDescText;

    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    private String delFlag;

    @ApiModelProperty("surveyName表关联id")
    private String surveyNameId;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("修改日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
