package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (SurveyQuestion)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:46
 */
@ApiModel
@Data
public class SurveyQuestion implements Serializable {
    private static final long serialVersionUID = -14320853560468834L;
    private String id;

    @ApiModelProperty("问题名称")
    private String question;

    @ApiModelProperty("问题的类型 0单选 1多选 2得分")
    private String answerType;

    @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
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
