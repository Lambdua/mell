package lt.school.mell.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.school.mell.security.CustomAuthenticationFilter;
import lt.school.mell.security.MyUserDetailService;
import lt.school.mell.utils.ajaxBean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@Configuration
public class MySecurityConfigureAdapter extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    MyUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .authorizeRequests() //开启登录配置
//                .antMatchers("/").permitAll() //访问index所需要的数据
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated() //表示剩余的别的接口，登录之后就可以访问
                .and()
                .formLogin() //开启登录校验
                .loginPage("/login") //定义登录界面
                .loginProcessingUrl("/userlogin") //登录处理接口
                .usernameParameter("username") // 接受的用户名key 默认为username
                .passwordParameter("password") //接受的密码key 默认为password
//                .successHandler((request, response, authentication) -> {
//
//                    RespBean ok = RespBean.error("登录成功");
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(ok));
//                    out.flush();
//                    out.close();
//                }) //登录成功的处理函数
//                .failureHandler(((request, response, exception) -> {
//                    RespBean error = RespBean.error("登录失败");
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(error));
//                    out.flush();
//                    out.close();
//                }))  //登录失败的处理函数
                .permitAll() //表单登录的所有请求全都直接通过
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutsuccess")
                .logoutSuccessHandler(((request, response, authentication) -> {
                    RespBean ok = RespBean.ok("注销成功");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(ok));
                    out.flush();
                    out.close();
                }))
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                    RespBean error = RespBean.ok("权限不足");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                }));
        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 2);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(((request, response, authentication) -> {
            RespBean ok = RespBean.ok("登录成功");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(ok));
            out.flush();
            out.close();
        }));
        filter.setAuthenticationFailureHandler(((request, response, exception) -> {
            RespBean error = RespBean.error("登录失败");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(error));
            out.flush();
            out.close();
        }));
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
