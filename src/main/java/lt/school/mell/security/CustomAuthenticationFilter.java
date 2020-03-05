package lt.school.mell.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/3/4
 **/
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            ObjectMapper objectMapper=new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest=null;
            try(InputStream is=request.getInputStream()) {
                Map<String,String> authenticationBean= objectMapper.readValue(is, Map.class);
                authRequest=new UsernamePasswordAuthenticationToken(
                        authenticationBean.get("username"),authenticationBean.get("password")
                );
            }catch (IOException e){
                e.printStackTrace();
                authRequest=new UsernamePasswordAuthenticationToken("","");
            }finally {
                setDetails(request,authRequest);
                return  this.getAuthenticationManager().authenticate(authRequest);
            }
        }else {
           return super.attemptAuthentication(request,response);

        }
    }
}
