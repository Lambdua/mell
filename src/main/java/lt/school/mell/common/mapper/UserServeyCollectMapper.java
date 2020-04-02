package lt.school.mell.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lt.school.mell.common.entity.UserServeyCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/31
 **/
@Mapper
public interface UserServeyCollectMapper extends BaseMapper<UserServeyCollect> {
    @Select("select group_id as groupId from USER_SERVEY_COLLECT where user_id='${userid}' group by group_id")
    List<String> selectGroups(String userId);
}
