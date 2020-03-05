package lt.school.mell.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lt.school.mell.entity.Users;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@Component
public interface UsersMapper extends BaseMapper<Users> {
    Users countbyId(Users users);
//
//    public static void main(String[] args) {
//        Users users=new Users();
//    }
}
