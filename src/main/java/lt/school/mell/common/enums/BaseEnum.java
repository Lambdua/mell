package lt.school.mell.common.enums;

import com.google.common.collect.ImmutableBiMap;
import lombok.Data;

import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/3/16
 **/
@Data
public class BaseEnum<T> {
    /*
     * 状态码
     * */
    private int status;
    private Boolean success;
    private String msg;
    private T data;

    public static BaseEnum UNKONW_ERROR() {
        return new BaseEnum(-100, false, "未知异常", null);
    }

    public static BaseEnum OPERATION_FAILURE() {
        return new BaseEnum(-101, false, "操作失败", null);
    }


    public static <T> BaseEnum UPDATE_SUCCESS(T data) {
        return new BaseEnum(200, true, "更新信息成功",getMap(data) );
    }

    public static BaseEnum UPDATE_SUCCESS() {
        return new BaseEnum(200, true, "更新信息成功", null);
    }


    public static BaseEnum GET_PARAM_FAILURE() {
        return new BaseEnum(-102, false, "获取参数失败", null);
    }

    private BaseEnum() {
    }

    public static <T> BaseEnum GET_SUCCESS(T data) {
        return new BaseEnum(200, true, "获取成功", getMap(data));
    }

    public static BaseEnum DELETE_SUCCESS() {
        return new BaseEnum(200, true, "删除成功", null);
    }

    public static BaseEnum SAVE_SUCCESS() {
        return new BaseEnum(200, true, "保存成功", null);
    }

    public static <T> BaseEnum SAVE_SUCCESS(T data) {
        return new BaseEnum(200, true, "保存成功", getMap(data));
    }


    public static BaseEnum SYSTEM_ERROR() {
        return new BaseEnum(-103, false, "系统异常", null);
    }

    public BaseEnum(int status, Boolean success, String msg, T data) {
        this.status = status;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Map<String, Object> getMap(T data) {
        Class<?> dataClass = data.getClass();
        if (data instanceof Map) {
            return (Map<String, Object>) data;
        }
        String name = dataClass.getSimpleName().toLowerCase();
        Map<String,Object> map= ImmutableBiMap.of(name,data);
        return map;
    }
}
