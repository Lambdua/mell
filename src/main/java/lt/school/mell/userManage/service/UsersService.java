package lt.school.mell.userManage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import lt.school.mell.common.entity.URelate;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.UsersStateEnum;
import lt.school.mell.common.mapper.URelateMapper;
import lt.school.mell.common.mapper.UsersMapper;
import lt.school.mell.security.MyPasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/14
 **/
@Service
@Transactional(readOnly = true)
@Slf4j
public class UsersService {

    Date today = new Date();

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    MyPasswordEncoder passwordEncoder;
    @Autowired
    URelateMapper uRelateMapper;

    public BaseEnum get(String id) {

        Users users = usersMapper.selectById(id);
        if (users != null) {
            return UsersStateEnum.GET_SUCCESS(users);
        }
        return UsersStateEnum.GET_EMPTR();
    }

    @Transactional(readOnly = false)
    public BaseEnum saveOrUpdate(Users entity) {


        entity.setUpdateDate(today);
        try {
            if (StringUtils.isBlank(entity.getId())) {
                //注册
                Users user = usersMapper.selectOne(new QueryWrapper<Users>().lambda().eq(Users::getUsername, entity.getUsername()));
                if (user != null) {
                    return UsersStateEnum.USERNAME_EXISTS();
                }
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                if (usersMapper.insert(entity) == 1) {
                    entity = usersMapper.selectOne(Wrappers.lambdaQuery(Users.class).eq(Users::getUsername, entity.getUsername()));
                    return UsersStateEnum.REGISTE_SUCCESS(entity);
                } else {
                    return UsersStateEnum.UNKONW_ERROR();
                }
            } else {
                if (usersMapper.update(entity, Wrappers.lambdaQuery(Users.class).eq(Users::getId, entity.getId())) == 1) {
                    return UsersStateEnum.UPDATE_SUCCESS(entity);
                } else {
                    return UsersStateEnum.OPERATION_FAILURE();
                }
            }
        } catch (Exception e) {
            log.error(entity.toString(), e);
            return UsersStateEnum.UNKONW_ERROR();
        }
    }

    @Transactional(readOnly = false)
    public UsersStateEnum remove(Users entity) {
/*
        if (StringUtils.isBlank(entity.getId())) {
            return false;
        }
        return usersMapper.delete(new QueryWrapper<>(entity)) == 1 ? true : false;


*/
        return null;
    }

    public UsersStateEnum<List<Users>> findList(Users entity) {
//        return usersMapper.selectList(new QueryWrapper<>(entity));
        return null;
    }


    public Users findOne(Users users) {
        return usersMapper.selectOne(new QueryWrapper<>(users));
    }

    public Users init(String username) {
        Users users = usersMapper.selectOne(Wrappers.lambdaQuery(Users.class).eq(Users::getUsername, username));
        return users;
//        return UsersStateEnum.GET_SUCCESS(users);
    }


    public BaseEnum getOtherInfo(String userId) {
        Users users = usersMapper.selectById(userId);
        if (!users.getMatchFlag().equals("2")) return UsersStateEnum.NULL_MATCH_USERS();
        else {
            URelate uRelate = uRelateMapper.selectOne(Wrappers.lambdaQuery(URelate.class)
                    .eq(URelate::getUserId1, userId)
                    .or().eq(URelate::getUserId2, userId));
            String otherId = uRelate.getUserId1().equals(userId) ? uRelate.getUserId2() : uRelate.getUserId1();
            Users other = usersMapper.selectById(otherId);
            other.setId("");
            return BaseEnum.GET_SUCCESS(other);
        }
    }
}
