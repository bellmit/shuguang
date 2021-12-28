package com.sofn.common.fileutil.dao;

import com.sofn.common.db.BeanHandler;
import com.sofn.common.db.JdbcTemplate;
import com.sofn.common.fileutil.FastDFSClient;
import com.sofn.common.fileutil.SysFileManageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author heyongjie
 * @date 2019/11/29 13:33
 */
public class SysFileManageDto {

    public SysFileManageVo getFileInfo(String id) {
        if (!StringUtils.isBlank(id)) {
            String sql = "SELECT\n" +
                    "\tA . ID,\n" +
                    "\tA .FILE_NAME fileName,\n" +
                    "\tA .FILE_PATH filePath,\n" +
                    "\tA.FILE_SIZE fileSize,\n" +
                    "  A.FILE_TYPE fileType,\n" +
                    "  A.OPERATOR \"operator\",\n" +
                    "  B.NICKNAME operatorName,\n" +
                    "  A.OPERATE_TIME operateTime,\n" +
                    "  A.SYSTEM systemId,\n" +
                    "  C.SUBSYSTEM_NAME systemName,\n" +
                    "  A.DOWNLOAD_TIMES  downloadTimes,\n" +
                    "  A.REMARK remark \n" +
                    "FROM\n" +
                    "\tSYS_FILE_MANAGE A \n" +
                    "LEFT JOIN SYS_USER B ON A.OPERATOR = B.ID AND B.DEL_FLAG = 'N'\n" +
                    "LEFT JOIN SYS_SUBSYSTEM C ON A.SYSTEM = C.ID OR A.SYSTEM = C.APP_ID AND C.DEL_FLAG = 'N' \n" +
                    "WHERE A.ID = ? AND A.ACTIVATION_STATE = 'Y' AND A.DEL_FLAG = 'N'";
            List<SysFileManageVo> list = JdbcTemplate.query(sql, new BeanHandler<>(SysFileManageVo.class),id);
            if(!CollectionUtils.isEmpty(list)){
                SysFileManageVo sysFileManageVo = list.get(0);
                sysFileManageVo.setFilePath(FastDFSClient.getRealPath(sysFileManageVo.getFilePath()));
                return list.get(0);
            }
        }
        return null;
    }

    public static void main(String[] args) {

        SysFileManageDto ss = new SysFileManageDto();
        SysFileManageVo f21dc4532a804220948e1b2296f8a338 = ss.getFileInfo("f21dc4532a804220948e1b2296f8a338");

        System.out.println(f21dc4532a804220948e1b2296f8a338);

    }

}
