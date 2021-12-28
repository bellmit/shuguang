/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.fastdfs;

import lombok.Data;

import java.io.Serializable;

/**
 * fastdfs文件信息
 *
 * @author lijiang
 * @create 2019-04-02 15:41
 **/
@Data
public class FastdfsFileInfo implements Serializable {
    private static final long serialVersionUID = -638994413913697056L;
    // the group name of storage server
    private String groupName;
    // filename on storage server
    private String remoteFileName;
}
