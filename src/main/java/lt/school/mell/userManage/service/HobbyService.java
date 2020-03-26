package lt.school.mell.userManage.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lt.school.mell.common.entity.Hobby;
import lt.school.mell.common.entity.Users;
import lt.school.mell.common.entity.UsersHobby;
import lt.school.mell.common.enums.BaseEnum;
import lt.school.mell.common.enums.HobbyEnum;
import lt.school.mell.common.mapper.HobbyMapper;
import lt.school.mell.common.mapper.UsersHobbyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public BaseEnum<Map<String,List>> findList(Hobby entity) {

        Map<String,Object> result=new HashMap<>();

        List<Hobby> hobbiesFather = hobbyMapper.selectList(Wrappers.lambdaQuery(Hobby.class).eq(Hobby::getHobbyLevel,1));
        for (Hobby hobby : hobbiesFather) {
            List<Hobby> childHobbies = hobbyMapper.selectList(Wrappers.lambdaQuery(Hobby.class).eq(Hobby::getParentId, hobby.getId()));
            result.put(hobby.getName(),childHobbies);
        }
        return HobbyEnum.GET_SUCCESS(result);
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
