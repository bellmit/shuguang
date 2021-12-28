package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.sys.mapper.SysMenuMapper;
import com.sofn.sys.model.SysMenu;
import com.sofn.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * Created by sofn
 */
@SuppressWarnings("ALL")
@Service(value = "sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {

}
