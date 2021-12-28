package com.sofn.common.fileutil;

/***
 * @desc 文件工具类
 * @author xl
 * @date 2021/03/30 13:56
 */
public class FileUtil {

    /***
     *  判断上传文件大小
     * @param len 文件长度
     * @param size 限制文件大小
     * @param unit 单位(M,G等)
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024/1024;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024/1024/1024;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
