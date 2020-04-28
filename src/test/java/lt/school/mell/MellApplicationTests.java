package lt.school.mell;

import lt.school.mell.common.entity.SurveyAnswer;
import lt.school.mell.common.entity.SurveyQuestion;
import lt.school.mell.common.mapper.SurveyAnswerMapper;
import lt.school.mell.common.mapper.SurveyNameMapper;
import lt.school.mell.common.mapper.SurveyQuestionMapper;
import lt.school.mell.common.mapper.SurveyResultMapper;
import lt.school.mell.util.POIUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static lt.school.mell.util.POIUtils.getValue;

@SpringBootTest
class MellApplicationTests {
    @Autowired
    SurveyQuestionMapper surveyQuestionMapper;
    @Autowired
    SurveyAnswerMapper answerMapper;
    @Autowired
    SurveyResultMapper resultMapper;
    @Autowired
    SurveyNameMapper surveyNameMapper;


//    private static SurveyName surveyName;
//    String file = "C:\\Users\\liangtao\\Desktop\\问卷批量插入表.xlsx";
    String file = "C:\\Users\\liangtao\\Desktop\\MBTI_1 (2).xlsx";



    @Test
    void insertSurvey() throws IOException {

        File locationFile = new File(file);
        FileInputStream fs = new FileInputStream(locationFile);
        Workbook workbok = POIUtils.getWorkbok(fs, locationFile);
        Sheet sheet = workbok.getSheetAt(0);
        //插入到问题表中
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            surveyQuestion.setId(Double.valueOf(getValue(row.getCell(0))+"").intValue()+"");
            surveyQuestion.setQuestion(String.valueOf(getValue(row.getCell(2))));
            surveyQuestion.setAnswerType("0");
            surveyQuestion.setSurveyNameId("5");
            surveyQuestionMapper.insert(surveyQuestion);
        }

        Sheet answerSheet = workbok.getSheetAt(1);
        for(Row row:answerSheet){
            if (row.getRowNum() == 0) {
                continue;
            }
            SurveyAnswer answer=new SurveyAnswer();
            answer.setQuestionId(Double.valueOf(getValue(row.getCell(0))+"").intValue()+"");
            answer.setChoicetext(String.valueOf(getValue(row.getCell(1))));
            answer.setSort((Double.valueOf(getValue(row.getCell(2))+"").intValue()));
            answer.setQuestionScore(String.valueOf(getValue(row.getCell(3))));
            answer.setSurveyNameId("5");
            answerMapper.insert(answer);
        }
    }

}
