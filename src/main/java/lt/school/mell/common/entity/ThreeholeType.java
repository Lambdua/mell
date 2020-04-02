package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
    @JsonIgnore
    private String delFlag;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("更新日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
