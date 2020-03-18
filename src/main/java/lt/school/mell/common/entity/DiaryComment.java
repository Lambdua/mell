package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (DiaryComment)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:44
 */
@ApiModel
@Data
public class DiaryComment implements Serializable {
    private static final long serialVersionUID = 795034598451847216L;
        private String id;

        @ApiModelProperty("评论内容")
        private String commentContent;

        @ApiModelProperty("日记id")
        private String diaryId;

        @ApiModelProperty("排序")
        private Integer sort;

        @ApiModelProperty("父类id")
        private String parentId;

        @ApiModelProperty("用户id")
        private String userId;

        @ApiModelProperty(value = "逻辑删除标志 0假1真",hidden = true)
        private String delFlag;

        @ApiModelProperty("创建日期")
        private Date createDate;

        @ApiModelProperty("修改日期")
        private Date updateDate;

}
