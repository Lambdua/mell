package lt.school.mell.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import lt.school.mell.common.mapper.UsersMapper;
import lt.school.mell.common.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
class UsersMapperTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void test(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        PageHelper.startPage(1,10);
        List<Users> list = usersMapper.selectList(wrapper);

        List<Users> users = usersMapper.selectList(null);
//        for (int i = 0; i < 20; i++) {
//            Users insertUser = new Users();
//            insertUser.setId("123dfadfa+"+i);
//            insertUser.setAge(213);
//            insertUser.setMatchFlag("1");
//            int insert = usersMapper.insert(insertUser);
//        }
/*
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        int delete = usersMapper.delete(wrapper.lambda().eq(Users::getId, "123"));
        System.out.println("-------"+delete+"-------------");
*/

//        Integer integer = usersMapper.selectCount(wrapper.lambda().eq(Users::getId, "123"));
//        usersMapper.selectCount(wrapper.lambda().eq())
//        Integer integer2 = usersMapper.selectCount(null);

//        System.out.println(users);
    }
}
