package com.sofn.core.base;

import com.github.pagehelper.PageInfo;
import com.sofn.core.Constants;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseService<P extends BaseProvider<T>, T extends BaseModel> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected P provider;
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /** 修改 */
    public void update(T record) {
        if (StringUtils.isBlank(record.getUpdateBy())) {
            record.setUpdateBy(WebUtil.getCurrentUserId());
        }
        Assert.isNotBlank(record.getId(), "ID");
        provider.update(record);
    }

    /** 新增 */
    public T add(T record) {
        //String uid = null;
        logger.debug("{}",record.getDelFlag());
        String uid = WebUtil.getCurrentUserId();
        if(StringUtils.isBlank(record.getDelFlag())){
            record.setDelFlag("N");
        }
        if (StringUtils.isBlank(record.getCreateBy())) {
            record.setCreateBy(uid == null ? "" : uid);
        }
        if (StringUtils.isBlank(record.getUpdateBy())) {
            record.setUpdateBy(uid == null ? "" : uid);
        }
        return provider.update(record);
    }

    /** 删除 */
    public void delete(String id) {
        Assert.isNotBlank(id, "ID");
        provider.delete(id, WebUtil.getCurrentUserId());
    }

    /** 根据Id查询 */
    @SuppressWarnings("unchecked")
    public T queryById(String id) {
        Assert.isNotBlank(id, "ID");
        StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
        String className = this.getClass().getSimpleName().replace("Service", "");
        sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1));
        sb.append(":").append(id);
        T record = (T)redisTemplate.opsForValue().get(sb.toString());

            try {
                record = provider.queryById(id);
            } catch (Exception e) {
                return null;
            }

        return record;
    }

    /** 条件查询 */
    public PageInfo<T> query(Map<String, Object> params) {
        return provider.query(params);
    }
}
