package lt.school.mell.common.ajaxBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lt.school.mell.common.enums.BaseEnum;

/**
 * @author liangtao
 * @Date 2020/3/4
 **/
@Data
@ApiModel(description = "返回结果")
public final class RespBean<T> {
    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty("对应状态码信息")
    private int status;
    @ApiModelProperty("结果描述信息")
    private String msg;
    @ApiModelProperty("业务数据")
    private T data;

    public RespBean(boolean success, int status, String msg, T data) {
        this.success = success;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public RespBean(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RespBean<T> success(T data) {
        return new RespBean<>(true, "SUCCESS", data);
    }

    public static RespBean success(String msg) {
        return new RespBean(true, msg, null);
    }

    public static <T> RespBean<T> success(String message, T data) {
        return new RespBean<>(true, message, data);
    }


    public static RespBean failure() {
        return new RespBean(false, "FAILURE", null);
    }

    public static RespBean failure(BaseEnum baseEnum) {
        return new RespBean(baseEnum.getSuccess(), baseEnum.getMsg(), null);
    }


    public static RespBean failure(String message) {
        return new RespBean(false, message, null);
    }

    public static <T> RespBean<T> result(BaseEnum<T> baseEnum) {
        return new RespBean(baseEnum.getSuccess(), baseEnum.getStatus(), baseEnum.getMsg(), baseEnum.getData());
    }


}
