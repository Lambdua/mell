package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:04:49
 */
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 115316870214775531L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 性别 1男0女
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 微信
     */
    private String wechatId;
    /**
     * qq
     */
    private String qq;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 是否开启匹配 0否1开
     */
    private String matchFlag;
    /**
     * 逻辑删除标志 0假1真
     */
    private String delFlag;
    /**
     * 请求匹配的用户id
     */
    private String wantUid;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 个人信息修改日期
     */
    private Date updateDate;


}