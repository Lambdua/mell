package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/3/31
 **/
public class SurveyEnum<T> extends BaseEnum<T> {
    public SurveyEnum(int status, Boolean success, String msg, T data) {
        super(status, success, msg, data);
    }


    public static BaseEnum NO_HAVE() {
        return new BaseEnum(301, true, "查询为空", null);
    }
}
