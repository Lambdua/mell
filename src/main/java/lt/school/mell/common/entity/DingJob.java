package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (DingJob)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:44
 */
@ApiModel
@Data
public class DingJob implements Serializable {
    private static final long serialVersionUID = -62433047334453539L;
    private String id;

    @ApiModelProperty("推送时间")
    private Date submitDate;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("推送方式 0 app内 1短信 2")
    private String type;

    @ApiModelProperty("推送内容")
    private String content;

    @ApiModelProperty("推送id")
    private String subUserId;

    @ApiModelProperty("接收id")
    private String receiveUserId;

    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    private String delFlag;

    @ApiModelProperty("修改日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
