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
 * (Diary)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:43
 */
@ApiModel
@Data
public class Diary implements Serializable {
    private static final long serialVersionUID = -94389340434877816L;
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("日记内容")
    private String diaryContent;

    @ApiModelProperty(value = "公开等级，0：自己可见 1：彼此可见 2：鱼塘可见 3：全部可见 ", notes = "默认设为1")
    private Integer openLevel;

    @ApiModelProperty("星星数/点赞数")
    private Integer starCount;

    @ApiModelProperty("日记标题")
    private String title;

    @JsonIgnore
    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    private String delFlag;

    @ApiModelProperty(value = "创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "修改日期", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;

}
