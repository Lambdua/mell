package lt.school.mell.square.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import lt.school.mell.common.entity.Threehole;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.ThreeholeEnum;
import lt.school.mell.common.mapper.ThreeholeMapper;
import lt.school.mell.common.mapper.ThreeholeTypeMapper;
import lt.school.mell.common.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/4/2
 **/
@Service
public class ThreeholeService {
    @Autowired
    ThreeholeMapper threeholeMapper;
    @Autowired
    ThreeholeTypeMapper threeholeTypeMapper;
    @Autowired
    UsersMapper usersMapper;


    public BaseEnum getTypes() {
        return BaseEnum.GET_SUCCESS(threeholeTypeMapper.selectList(null));
    }

    public BaseEnum getThreeholeByType(int pageNum, int pageSize, String type) {
        PageHelper.offsetPage(pageNum, pageSize);
        List<Threehole> threeholes = threeholeMapper.selectList(Wrappers.lambdaQuery(Threehole.class)
                .eq(Threehole::getType, type));

        if (threeholes == null || threeholes.size() == 0) {
            return ThreeholeEnum.NO_HOAVE();
        } else {
            for (Threehole threehole : threeholes) {
                if (!Boolean.valueOf(threehole.getIsMask())) {
                    Users users = usersMapper.selectById(threehole.getUserId());
                    threehole.setNickName(users.getNickName());
                    threehole.setAvatar(users.getAvatar());
                }
            }
        }
        return BaseEnum.GET_SUCCESS(threeholes);

    }

    public BaseEnum saveThreehole(Threehole threehole) {
        threeholeMapper.insert(threehole);
        return BaseEnum.SAVE_SUCCESS();
    }



}
