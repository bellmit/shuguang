package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.sys.mapper.SysLogMapper;
import com.sofn.sys.model.SysLog;
import com.sofn.sys.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * Created by sofn
 */
@SuppressWarnings("ALL")
@Service(value = "sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements SysLogService {

}
