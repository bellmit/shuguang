package com.sofn.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用实体（通用字段）
 *
 * @author quzhijie
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseModel<T extends BaseModel> extends Model<BaseModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 设置创建人 创建时间  del_flag字段
     *
     * @return 数据ID
     */
    public String preInsert() {
        if (StringUtils.isBlank(getId())) {
            setId(IdUtil.getUUId());
        }
        try {
            String userId = UserUtil.getLoginUserId();
            if (StringUtils.isNotBlank(userId)) {
                this.updateUserId = userId;
                this.createUserId = userId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.delFlag = "N";
        this.updateTime = new Date();
        this.createTime = this.updateTime;

        return getId();
    }

    /**
     * 设置修改人 修改时间
     */
    public void preUpdate() {
        try {
            String userId = UserUtil.getLoginUserId();
            if (StringUtils.isNotBlank(userId)) {
                this.updateUserId = userId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.updateTime = new Date();
    }

    /**
     * 创建/修改预处理
     */
    public void pre() {
        if (org.apache.commons.lang3.StringUtils.isBlank(this.getId())) {
            this.preInsert();
        } else {
            this.preUpdate();
        }
    }
}
