package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/3/20
 **/
public class SquareEnum<T> extends BaseEnum<T> {
    public SquareEnum(int status, Boolean success, String msg, T data) {
        super(status, success, msg, data);
    }
}
