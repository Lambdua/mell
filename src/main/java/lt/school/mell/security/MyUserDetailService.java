package lt.school.mell.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lt.school.mell.entity.Users;
import lt.school.mell.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UsersMapper usersMapper;
//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String password=passwordEncoder.encode("123");

        Users users = usersMapper.selectOne(new QueryWrapper<>(new Users()).lambda().eq(Users::getUsername, username));
        //给这个用户授予admin权限
        if (users==null){
            throw new UsernameNotFoundException("查询步到");
        }

//        log.info("登录， 用户名："+username+users.getPassword());
        return new User(username,users.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
    }
}
