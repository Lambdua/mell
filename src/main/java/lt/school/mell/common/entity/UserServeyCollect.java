package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserServeyCollect)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:47
 */
@ApiModel
@Data
public class UserServeyCollect implements Serializable {
    private static final long serialVersionUID = 310705539248049742L;
    private String id;

    @ApiModelProperty("用户表id")
    private String userId;

    @ApiModelProperty("测试问卷id")
    private String serveyId;

    @ApiModelProperty("测试问卷结果id")
    private String servuyResultId;

    @ApiModelProperty("该结果得分值")
    private Integer resultScore;

    @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
    private String delFlag;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("修改日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
