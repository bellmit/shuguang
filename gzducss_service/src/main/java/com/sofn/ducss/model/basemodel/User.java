package com.sofn.ducss.model.basemodel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by sofn
 */
@Data
public class User extends BaseModel<User> {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态 启用、禁用
     */
    private String status;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 初始密码(未加密)
     */
    private String initPassword;

    /**
     * 加密盐值
     */
    @JSONField(serialize = false)
    private String salt;

    /**
     * 所属机构ID
     */
    private String organizationId;

    private String remark;

    /**
     * 性别
     */
    private Integer sex;





    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
