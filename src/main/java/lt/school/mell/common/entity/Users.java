package lt.school.mell.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Users)实体类
 *
 * @author liangtao
 * @since 2020-03-16 18:36:47
 */
@ApiModel
@Data
public class Users implements Serializable {
    public Users(String id) {
        this.id = id;
    }

    public Users(){
    }

    private static final long serialVersionUID = 377342962202806819L;
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("星座")
    private String constellation;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("性别 1男0女")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("微信")
    private String wechatId;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("个性签名")
    private String signature;

    @ApiModelProperty("是否开启匹配 0否1开2成功3失败")
    private String matchFlag;

    @ApiModelProperty(value = "逻辑删除标志 0假1真", hidden = true)
    private String delFlag;

    @ApiModelProperty("请求匹配的用户id")
    private String wantUid;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("个人信息修改日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Users)) {
            return false;
        }
        Users users = (Users) o;
        return new EqualsBuilder()
                .append(id, users.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }


    //建立关系后进行的用户预处理更新操作
    public void relateBuildHandler(){
        this.setUpdateDate(new Date());
        this.setMatchFlag("2");
        this.setWantUid("");
    }
}
