package lt.school.mell.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/3/5
 **/
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
//        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }


    public static void main(String[] args) {
        MyPasswordEncoder myPasswordEncoder = new MyPasswordEncoder();
        System.out.println(myPasswordEncoder.encode("123"));
    }
}
