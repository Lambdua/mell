package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/3/17
 **/
public class DiaryEnum<T> extends BaseEnum {


    public DiaryEnum(int statue, Boolean success, String msg, Object data) {
        super(statue, success, msg, data);
    }

    public static BaseEnum QUERY_NULL() {
        return new BaseEnum(-200, false, "查询为空", null);
    }

//    public static BaseEnum

}