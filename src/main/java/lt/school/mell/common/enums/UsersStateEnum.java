package lt.school.mell.common.enums;

/**
 * @author liangtao
 * @Date 2020/3/14
 **/
public class UsersStateEnum<T> extends BaseEnum<T> {
    public UsersStateEnum(int statue, Boolean success, String msg, T data) {
        super(statue, success, msg, data);
    }

    public static BaseEnum USERNAME_EXISTS() {
        return new BaseEnum(-300, false, "用户名已存在", null);
    }

    public static BaseEnum USERNAME_OR_PWD_MISTAKE() {
        return new BaseEnum(-301, false, "用户名或密码错误", null);
    }

    public static BaseEnum PHONE_EXISTS() {
        return new BaseEnum(-302, false, "手机号以注册", null);
    }

    public static BaseEnum Match_HAVE() {
        return new BaseEnum(303, false, "用户以匹配", null);
    }

    public static BaseEnum WANT_HAVE() {
        return new BaseEnum(304, false, "用户以申请匹配特定人", null);
    }

    public static <T> BaseEnum LOGIN_SUCCESS(T data) {
        return new BaseEnum(200, true, "登录成功",getMap(data));
    }


    public static <T> BaseEnum REGISTE_SUCCESS(T data) {
        return new BaseEnum(200, true, "注册成功", getMap(data));
    }

    public static BaseEnum NULL_MATCH_USERS() {
        return new BaseEnum(305, true, "尚未有匹配用户", null);
    }


    public static BaseEnum GET_EMPTR() {
        return new BaseEnum(-306, false, "查询为空", null);
    }
}
