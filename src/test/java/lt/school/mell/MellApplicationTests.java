package lt.school.mell;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MellApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        int j = 73;
        for (int i = 133; i < 200;) {
            System.out.println("update survey_answer set QUESTION_SCORE='绿' where id='" + (i++) + "';");
            System.out.println("update survey_answer set QUESTION_SCORE='黄' where id='" + (i++) + "';");
            System.out.println("update survey_answer set QUESTION_SCORE='蓝' where id='" + (i++) + "';");
            System.out.println("update survey_answer set QUESTION_SCORE='红' where id='" + (i++) + "';");
        }
    }
}
