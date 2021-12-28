package com.sofn.sys.vo;

import com.sofn.sys.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/7/16 11:12
 */
@Data
public class UserHaveRoleVo {

    private String userId;

    private List<SysRole> sysRoles;


}
