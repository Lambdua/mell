package lt.school.mell.userManage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.HobbyEnum;
import lt.school.mell.common.entity.Hobby;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.entity.UsersHobby;
import lt.school.mell.common.mapper.HobbyMapper;
import lt.school.mell.common.mapper.UsersHobbyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/16
 **/
@Service
public class HobbyService  {
    @Autowired
    HobbyMapper hobbyMapper;
    @Autowired
    UsersHobbyMapper usersHobbyMapper;

    //    Date today=new Date();

    public HobbyEnum<Hobby> get(String id) {
        return null;
    }

    public HobbyEnum<Hobby> saveOrUpdate(Hobby entity) {
        return null;
    }

    public HobbyEnum<Hobby> remove(Hobby entity) {
        return null;
    }

    public BaseEnum<List<Hobby>> findList(Hobby entity) {

        List<Hobby> hobbies = hobbyMapper.selectList(new QueryWrapper<>(null));
        if (hobbies != null) {
            return HobbyEnum.GET_SUCCESS(hobbies);
        }
        return (HobbyEnum<List<Hobby>>) HobbyEnum.OPERATION_FAILURE();
    }

/*
    public List<Hobby> findList(Hobby entity) {
        return hobbyMapper.selectList(new QueryWrapper<>(null));
    }
*/


    public List<Hobby> findListByParentId() {
        return null;
    }

    public BaseEnum saveUserHobbys(Users users, List<Hobby> hobbyList) {
        String id = users.getId();
        hobbyList.forEach(hobby -> {
            UsersHobby usersHobby = new UsersHobby();
            usersHobby.setUsersId(id);
            usersHobby.setHobbyId(hobby.getId());
            usersHobbyMapper.insert(usersHobby);
        });
        return HobbyEnum.SAVE_SUCCESS();

    }

}
