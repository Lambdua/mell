package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SurveyAnswer)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:45
 */
@ApiModel
@Data
public class SurveyAnswer implements Serializable {
    private static final long serialVersionUID = 729906761177782780L;
    private String id;

    @ApiModelProperty("question表id")
    private String questionId;

    @ApiModelProperty("选项字符串")
    private String choicetext;

    @ApiModelProperty("选项排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
    private String delFlag;

    @ApiModelProperty("surveyName表关联id")
    private String surveyNameId;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("修改日期")
    private Date updateDate;

}
