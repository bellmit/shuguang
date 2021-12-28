package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.JinjiPreExpandDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author hyl
 * @date 2018/9/29 15:01
 */
@DubboService(interfaceClass = JinJiPreProvider.class)
public class JinjiPreProviderImpl implements JinJiPreProvider{

    @Autowired
    private JinjiPreExpandDao jinjiPreExpandDao;

    @Override
    public List<Map<String, Object>> getDatas(String table) {
        return jinjiPreExpandDao.getDatas(table);
    }
}
