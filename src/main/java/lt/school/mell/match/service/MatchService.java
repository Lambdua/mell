package lt.school.mell.match.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lt.school.mell.common.entity.MatchDemand;
import lt.school.mell.common.entity.URelate;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.MatchEnum;
import lt.school.mell.common.enums.UsersStateEnum;
import lt.school.mell.common.mapper.URelateMapper;
import lt.school.mell.common.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/18
 **/
@Service
public class MatchService {
    @Autowired
    URelateMapper uRelateMapper;
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    MatchDemandService matchDemandService;
//    MatchDemandMapper matchDemandMapper;

    public BaseEnum startMatch(String userId, MatchDemand matchDemand) {
        Users users = usersMapper.selectById(userId);
        //检测是否已有
        URelate uRelate = uRelateMapper.selectOne(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, userId)
                .or().eq(URelate::getUserId2, userId));

        if (uRelate != null) {
            //用户以匹配，更新用户的matchFlag
            users.setMatchFlag("2");
//            updateUsers.setMatchFlag("2");
            int update = usersMapper.updateById(users);
            return UsersStateEnum.Match_HAVE();
        }

        //没有开启匹配,但是有想匹配的对象
        if (StringUtils.isNotBlank(users.getWantUid())) {
            return UsersStateEnum.WANT_HAVE();
        } else {

            //上传匹配信息
            matchDemandService.saveOrUpdate(matchDemand);

            users.setMatchFlag("1");
            usersMapper.updateById(users);
            return UsersStateEnum.UPDATE_SUCCESS(users);
        }
    }

    public BaseEnum wantMatch(String userId, String username) {
        //根据用户名查询
        Users ownUsers = usersMapper.selectById(userId);
        Users users = usersMapper.selectOne(Wrappers.lambdaQuery(Users.class).eq(Users::getUsername, username));
        //用户不存在，或者用户以匹配
        if (users == null || !users.getMatchFlag().equals("2")) {
            return UsersStateEnum.Match_HAVE();
        }
        ownUsers.setWantUid(users.getId());
        usersMapper.updateById(ownUsers);
        return UsersStateEnum.UPDATE_SUCCESS(ownUsers);
    }

    public BaseEnum findMatches(String userId) {
        List<Users> usersList = usersMapper.selectList(Wrappers.lambdaQuery(Users.class)
                .eq(Users::getWantUid, userId));

        if (usersList.size() > 0) {
            return UsersStateEnum.GET_SUCCESS(usersList);
        } else {
            return UsersStateEnum.GET_EMPTR();
        }
    }


    @Transactional(readOnly = false)
    public BaseEnum allowMatch(String userId, String otherId) {
        Users own = usersMapper.selectById(userId);
        Users otherUsers = usersMapper.selectById(otherId);
        //对方希望匹配的不是该用户，重置
        if (!otherUsers.getWantUid().equals(userId)) {
            otherUsers.setWantUid("");
            own.setWantUid("");
            usersMapper.updateById(own);
            usersMapper.updateById(otherUsers);
            return UsersStateEnum.OPERATION_FAILURE();
        }


        //更新双方为匹配成功
        own.setWantUid("");
        own.setMatchFlag("2");
        usersMapper.updateById(own);
        otherUsers.setMatchFlag("2");
        otherUsers.setWantUid("");
        usersMapper.updateById(otherUsers);

//        删除两者相关数据
        uRelateMapper.delete(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, userId)
                .or().eq(URelate::getUserId2, userId));

        uRelateMapper.delete(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, otherId)
                .or().eq(URelate::getUserId2, otherId));
        //入库到relate表中
        URelate relate = new URelate();
        relate.setUserId1(otherId);
        relate.setUserId2(userId);
        uRelateMapper.insert(relate);

        return UsersStateEnum.UPDATE_SUCCESS();
    }

    @Transactional(readOnly = false)
    public BaseEnum denyMatch(String userId, String otherId) {


        Users matchUsers = usersMapper.selectById(otherId);
        if (!matchUsers.getWantUid().equals(userId)) {
            return UsersStateEnum.UPDATE_SUCCESS();
        }
        matchUsers.setWantUid("");
        matchUsers.setMatchFlag("3");
        usersMapper.updateById(matchUsers);
        return UsersStateEnum.UPDATE_SUCCESS();
    }

    @Transactional(readOnly = false)
    public BaseEnum cancleMatch(String userId) {
        //更新用户
        Users users = usersMapper.selectById(userId);
        users.setMatchFlag("0");
        users.setWantUid("");
        usersMapper.updateById(users);

        //更新匹配关系
        List<URelate> uRelates = uRelateMapper.selectList(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, userId)
                .or().eq(URelate::getUserId2, userId));
        if (uRelates.size() > 0) {
            //存在对方用户
            URelate uRelate = uRelates.get(0);
            //更新对方的匹配关系
            String matchUsersId = uRelate.getUserId1().equals(userId) ? uRelate.getUserId2() : uRelate.getUserId1();
            Users matchUsers = new Users(matchUsersId);
            matchUsers.setMatchFlag("0");
            matchUsers.setWantUid("");
            usersMapper.updateById(matchUsers);
        } else {
            //尚没有匹配成功，直接删除匹配要求
            matchDemandService.deleteByUserId(userId);
        }
        return MatchEnum.UPDATE_SUCCESS(users);
    }
}

