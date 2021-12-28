package com.sofn.ducss.model.basemodel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.UserUtil;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
}
