package lt.school.mell.security;

import lombok.extern.slf4j.Slf4j;
import lt.school.mell.common.entity.Users;
import lt.school.mell.userManage.service.UsersService;
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
    UsersService usersService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users condition = new Users();
        condition.setUsername(username);
        Users users = usersService.findOne(condition);
        //给这个用户授予admin权限
        if (users==null){
            throw new UsernameNotFoundException("查询步到");
        }
        return new User(username,users.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
    }
}
