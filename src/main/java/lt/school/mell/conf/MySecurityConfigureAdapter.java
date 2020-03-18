package lt.school.mell.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.school.mell.common.ajaxBean.RespBean;
import lt.school.mell.security.CustomAuthenticationFilter;
import lt.school.mell.security.MyPasswordEncoder;
import lt.school.mell.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @author liangtao
 * @Date 2020/3/3
 **/
@Configuration
@EnableWebSecurity
public class MySecurityConfigureAdapter extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return new MyPasswordEncoder();
    }


    @Autowired
    MyUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/user/open/**").permitAll()
                .antMatchers("/user/hobby/test/**").permitAll()
//                .antMatchers("//hobby/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() //开启登录校验
//                .loginPage("/login") //定义登录界面
//                .defaultSuccessUrl()
                .loginProcessingUrl("/user/login") //登录处理接口
//                .successHandler()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(((request, response, authentication) -> {
                    RespBean ok = RespBean.success("注销成功");
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
                    RespBean error = RespBean.failure("权限不足");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                }));


        http.httpBasic().and().csrf().disable();
        http.addFilterAfter(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
//        .antMatchers("/csrf");

    }


    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(((request, response, authentication) -> {
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            request.setAttribute("username",principal.getUsername());
            request.setAttribute("pwd",principal.getPassword());
            request.getRequestDispatcher("/user/index").forward(request, response);
/*
            RespBean ok = RespBean.success("登录成功");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(ok));
            out.flush();
            out.close();
*/
        }));
        filter.setAuthenticationFailureHandler(((request, response, exception) -> {
            if (exception instanceof BadCredentialsException ||
                    exception instanceof UsernameNotFoundException) {
                //用户,密码错误
                RespBean error = RespBean.failure("用户名或者密码错误");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(error));
                out.flush();
                out.close();
            }
            if (exception instanceof CredentialsExpiredException) {
                response.sendRedirect("/login");
            } else {
                RespBean error = RespBean.failure("登录失败");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(error));
                out.flush();
                out.close();

            }
        }));
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
