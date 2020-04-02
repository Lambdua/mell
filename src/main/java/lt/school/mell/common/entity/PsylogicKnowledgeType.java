package lt.school.mell.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (PsylogicKnowledgeType)实体类
 * 文章类别表
 * @author liangtao
 * @since 2020-04-01 20:13:51
 */
@ApiModel
@Data
public class PsylogicKnowledgeType implements Serializable {
    private static final long serialVersionUID = -46563863324788521L;
    private String id;

    @ApiModelProperty("文章类别")
    private String type;

    @TableField(exist = false)
    private List<PsychologicalKnowledge> psychologicalKnowledgeList;


    private String delFlag;
}
