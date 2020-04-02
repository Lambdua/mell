package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/4/2
 **/
public class ThreeholeEnum<T> extends BaseEnum<T> {
    public ThreeholeEnum(int status, Boolean success, String msg, T data) {
        super(status, success, msg, data);
    }


    public static BaseEnum NO_HOAVE(){
        return new BaseEnum(401,true,"没有该类型的树洞内容",null);
    }
}
