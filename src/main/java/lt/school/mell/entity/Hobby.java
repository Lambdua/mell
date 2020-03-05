package lt.school.mell.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Hobby)实体类
 *
 * @author makejava
 * @since 2020-03-03 11:30:32
 */
@Data
public class Hobby implements Serializable {
    private static final long serialVersionUID = 921745144249377977L;
    
    private String id;
    /**
    * 爱好名称
    */
    private String name;
    /**
    * 级别 0级别最高，依次递减
    */
    private Integer hobbyLevel;
    /**
    * 父类id
    */
    private String parentId;
    /**
    * 爱好描述
    */
    private String description;
    /**
    * 对应图标
    */
    private String hobbyImg;



}