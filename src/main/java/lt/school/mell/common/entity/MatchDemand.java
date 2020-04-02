package lt.school.mell.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (MatchDemand)实体类
 *
 * @author liangtao
 * @since 2020-03-20 09:25:41
 */
@ApiModel
@Data
public class MatchDemand implements Serializable {
    private static final long serialVersionUID = 240507057389044767L;
    //用户UserId
    private String id;

    private String userId;

    @ApiModelProperty("最大年龄 默认100")
    private Integer maxAge;

    @ApiModelProperty("最小年龄 默认0")
    private Integer minAge;

    private String sex;

    @ApiModelProperty("1同类 0不同类")
    private String issimilar;

    private String delFlag;

    @ApiModelProperty("优先度， 默认为1, 3时只按照性别匹配")
    private Integer sort;


/*
    public boolean allEquals(Object o){
        if (this == o) {
            return true;
        }

        if (!(o instanceof MatchDemand)) {
            return false;
        }

        MatchDemand matchDemand= (MatchDemand) o;
        return this

    }
*/
}
