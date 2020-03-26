package lt.school.mell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lt.school.mell")
//@EnableScheduling
public class MellApplication {

    public static void main(String[] args) {
        SpringApplication.run(MellApplication.class, args);
    }

}
