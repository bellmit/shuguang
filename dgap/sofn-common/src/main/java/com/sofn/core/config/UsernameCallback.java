/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.config;

import com.sofn.core.util.SecurityUtil;

import javax.security.auth.callback.NameCallback;

/**
 * 数据库用户名回调类
 *
 * @author lijiang
 * @create 2019-07-15 15:01
 **/
public final class UsernameCallback extends NameCallback {
    private static final byte[] KEY = {9, -1, 13, 5, 3, 7, 11, 19};
    private static final long serialVersionUID = 2085103086141063747L;

    public UsernameCallback() {
        super("druidDataSource encrypted username", "none");
    }

    @Override
    public String getName() {
        return SecurityUtil.decryptDes(super.getName(), KEY);
    }

    // 生成加密后的数据库用户名
    public static void main(String[] args) {
        if (args.length > 0) {
            String username = args[0];
            String encryptedUsername = SecurityUtil.encryptDes(username, KEY);
            System.out.println(encryptedUsername);
        }
    }
}
