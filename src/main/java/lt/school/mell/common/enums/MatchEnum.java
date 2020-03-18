package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/3/18
 **/
public class MatchEnum<T> extends BaseEnum<T> {


    public MatchEnum(int statue, Boolean success, String msg, T data) {
        super(statue, success, msg, data);
    }

    public static <T> BaseEnum OTHER_HAVE() {
//        return new BaseEnum(true, "对方", data);
        return null;
    }



}
