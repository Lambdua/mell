package lt.school.mell.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Threehole)实体类
 *
 * @author liangtao
 * @since 2020-04-02 12:19:27
 */
@ApiModel
@Data
public class Threehole implements Serializable {
    private static final long serialVersionUID = 309896092117364004L;
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户表id")
    private String userId;

    @ApiModelProperty("内容类型")
    private String type;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("相应得分")
    private Integer score;

    @ApiModelProperty("删除标志")
    @JsonIgnore
    private String delFlag;

    @ApiModelProperty("是否匿名")
    private String isMask;

    @ApiModelProperty("用户昵称")
    @TableField(exist = false)
    private String nickName;

    @ApiModelProperty("头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("更新日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
