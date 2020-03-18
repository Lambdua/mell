package lt.school.mell.conf;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * @author liangtao
 * @Date 2020/3/13
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean("UserApis")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .securitySchemes(security())
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private List<ApiKey> security() {
        return Arrays.asList(new ApiKey("token", "token", "header")
        );
    }

   /* @Bean("CustomApis")
    public Docket customApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("客户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/custom.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("meill系统接口文档")
                .description("初版本")
                .termsOfServiceUrl("https://github.com/Lambdua/mell")
                .version("1.0")
                .build();
    }


}
