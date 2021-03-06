package lt.school.mell.match.job;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import lt.school.mell.common.entity.MatchDemand;
import lt.school.mell.common.entity.URelate;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.entity.UsersHobby;
import lt.school.mell.common.mapper.MatchDemandMapper;
import lt.school.mell.common.mapper.URelateMapper;
import lt.school.mell.common.mapper.UsersHobbyMapper;
import lt.school.mell.common.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liangtao
 * @Date 2020/3/18
 **/
@Slf4j
@Component
@Transactional(readOnly = true)
public class MatchJob {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    URelateMapper uRelateMapper;

    @Autowired
    MatchDemandMapper matchDemandMapper;

    @Autowired
    UsersHobbyMapper usersHobbyMapper;

    /*
        每天十点二十执行一次
     */
    @Scheduled(cron = "0 20 10 * * ? ")
    public void matchScheduled() {
        log.info("===================匹配开始=======================");

        //查询所有开启匹配的用户
        List<Users> usersList = usersMapper.selectList(Wrappers.lambdaQuery(Users.class)
                .eq(Users::getMatchFlag, "1")
                .and(i -> i.eq(Users::getWantUid, "").or().isNull(Users::getWantUid)));

        Map<Users, MatchDemand> usersMap = new HashMap<>();
        usersList.forEach(u -> {
            MatchDemand matchDemand = matchDemandMapper.selectOne(Wrappers.lambdaQuery(MatchDemand.class).eq(MatchDemand::getUserId, u.getId()));
            usersMap.put(u, matchDemand);
        });

        //三遍轮询，高匹配度 中匹配度  高优先度
        Users matchUsers = null;
        //高匹配度
        for (Users users : usersList) {
            matchUsers = null;
            if (usersMap.containsKey(users)) {
                //获取该用户的兴趣特征
                String usersId = users.getId();
                List<UsersHobby> usersHobbies = usersHobbyMapper.selectList(Wrappers.lambdaQuery(UsersHobby.class)
                        .eq(UsersHobby::getUsersId, usersId));

                //获取基本相同爱好的
                matchUsers = getHightMatch(users, usersMap, usersHobbies);

                if (matchUsers != null) {
                    log.info(usersId + " 匹配到100%要求");
                    matchSuccessHandler(users, matchUsers, usersMap);
                }
            }
        }


        //中匹配度
        usersList = new ArrayList<>(usersMap.keySet());
        for (Users users : usersList) {
            matchUsers = null;
            if (usersMap.containsKey(users)) {
                String usersId = users.getId();
                List<UsersHobby> usersHobbies = usersHobbyMapper.selectList(Wrappers.lambdaQuery(UsersHobby.class)
                        .eq(UsersHobby::getUsersId, usersId));
                matchUsers = getMiddleMatch(users, usersMap, usersHobbies);
                if (matchUsers != null) {
                    log.info(usersId + " 匹配到50%要求");
                    matchSuccessHandler(users, matchUsers, usersMap);
                }
            }
        }


        //高优先度
        usersList = new ArrayList<>(usersMap.keySet());
        for (Users users : usersList) {
            matchUsers = null;
            if (usersMap.containsKey(users)) {
                MatchDemand matchDemand = usersMap.get(users);
                Integer sort = matchDemand.getSort();
                if (sort < 3) {
                    matchDemand.setSort(sort + 1);
                    matchDemandMapper.updateById(matchDemand);
                } else {
                    String usersId = users.getId();
                    matchUsers = getpriorityMatch(users, matchDemand, usersMap);
                    if (matchUsers != null) {
                        log.info(usersId + " 随机匹配");
                        matchSuccessHandler(users, matchUsers, usersMap);
                    }
                }
            }
        }

        log.info("=============匹配结束=======================");

    }

    @Transactional(readOnly = false)
    void matchSuccessHandler(Users users, Users matchUsers, Map<Users, MatchDemand> usersMap) {

        //清除map中两个users对象
        usersMap.remove(users);
        if (usersMap.containsKey(matchUsers)) {
            usersMap.remove(matchUsers);
        }

        //删除matchDemand
        matchDemandMapper.delete(Wrappers.lambdaQuery(MatchDemand.class)
                .eq(MatchDemand::getUserId, users.getId())
                .or().eq(MatchDemand::getUserId, matchUsers.getId()));

        //更新两个users的状态
        users.relateBuildHandler();
        matchUsers.relateBuildHandler();
        usersMapper.updateById(users);
        usersMapper.updateById(matchUsers);

        //删除两者以前的关联记录
        String usersId = users.getId();
        uRelateMapper.delete(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, usersId)
                .or().eq(URelate::getUserId2, usersId));
        String matchUsersId = matchUsers.getId();
        uRelateMapper.delete(Wrappers.lambdaQuery(URelate.class)
                .eq(URelate::getUserId1, matchUsersId)
                .or().eq(URelate::getUserId2, matchUsersId));

        //两者建立关系
        URelate uRelate = new URelate();
        uRelate.setUserId1(usersId);
        uRelate.setUserId2(matchUsersId);
        uRelateMapper.insert(uRelate);
    }

    Users getpriorityMatch(Users users, MatchDemand matchDemand, Map<Users, MatchDemand> usersMap) {
        AtomicReference<Users> result = new AtomicReference<>();
        String sex = matchDemand.getSex();
        usersMap.forEach((k, v) -> {
            //查询不是自己，且优先级大于3,符合性别要求的
            if (!k.equals(users) && v.getSort() >= 3 && k.getSex().equals(sex)) {
                result.set(k);
            }
        });

        return result.get();
    }

    @Transactional(readOnly = false)
    Users getMiddleMatch(Users users, Map<Users, MatchDemand> usersMap, List<UsersHobby> usersHobbies) {
        MatchDemand matchDemand = usersMap.get(users);
        AtomicReference<Users> atomicUsers = new AtomicReference<>();
        //
        usersMap.forEach((k, v) -> {
            if (!k.equals(users)
                    && k.getAge() <= matchDemand.getMaxAge()
                    && k.getAge() >= matchDemand.getMinAge()
                    && k.getSex().equals(matchDemand.getSex())
            ) {
                atomicUsers.set(k);
            }
        });

        return atomicUsers.get();
    }

    Users getHightMatch(Users users, Map<Users, MatchDemand> usersMap, List<UsersHobby> usersHobbies) {
        MatchDemand matchDemand = usersMap.get(users);
        AtomicReference<Users> atomicUsers = new AtomicReference<>();
        //
        usersMap.forEach((k, v) -> {
            if (!k.equals(users)
                    && k.getAge() <= matchDemand.getMaxAge()
                    && k.getAge() >= matchDemand.getMinAge()
                    && k.getSex().equals(matchDemand.getSex())
            ) {
                //进行hobbyies的筛选
                List<UsersHobby> matchUsersHobbies = usersHobbyMapper.selectList(Wrappers.lambdaQuery(UsersHobby.class).eq(UsersHobby::getUsersId, k.getId()));
                //两者hobbies进行百分比判断 ,有相同的三项即可
                int sameHobbies = getSameHobbies(usersHobbies, matchUsersHobbies);
                if (sameHobbies >= 3) {
                    atomicUsers.set(k);
                }
            }
        });
        return atomicUsers.get();
    }

    private int getSameHobbies(List<UsersHobby> usersHobbies, List<UsersHobby> matchUsersHobbies) {
        AtomicInteger sameHobbies = new AtomicInteger();

        List<String> uhIds = new ArrayList<>();
        usersHobbies.forEach(h -> uhIds.add(h.getHobbyId()));

        matchUsersHobbies.forEach(h -> {
            if (uhIds.contains(h.getHobbyId())) {
                sameHobbies.getAndIncrement();
            }
        });
        return sameHobbies.get();
    }


}
