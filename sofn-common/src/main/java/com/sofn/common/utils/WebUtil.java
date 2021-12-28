package com.sofn.common.utils;

import com.github.pagehelper.PageInfo;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.core.ExcelExport;
import com.sofn.common.model.PagingParam;
import com.sofn.common.service.PagingQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public final class WebUtil {

    private final HttpServletRequest request;

    /**
     * 实例
     */
    private static final WebUtil instance = new WebUtil();

    /**
     * 构造函数
     */
    private WebUtil() {
        this.request = null;
    }

    /**
     * 构造函数
     */
    public WebUtil(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取实例
     *
     * @author 赵卉华
     * @return WebUtil实例
     */
    public static WebUtil getInstance() {
        return instance;
    }

    /**
     * 返回当前请求对象
     *
     * @return 当前请求对象
     */
    public HttpServletRequest getRequest() {
        if (request != null) {
            return request;
        }
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 session
     *
     * @return session；session不存在时创建一个；
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取 session
     *
     * @param create SESSION不存在时是否创建
     * @return session
     */
    public HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    /**
     * 获取 session id
     *
     * @return session
     */
    public String getSessionId() {
        HttpSession session = getSession(false);
        return session == null ? null : session.getId();
    }

    /**
     * 设置SESSION属性
     *
     * @author 赵卉华
     * @param name 属性名称
     * @param value 属性值
     */
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = getSession();
        session.setAttribute(name, value);
    }

    /**
     * 获取session 属性
     *
     * @param name 属性名称
     * @return 属性值；session 不存在时返回null；session中没有该熟属性时返回null
     */
    public Object getSessionAttribute(String name) {
        HttpSession session = getRequest().getSession(false);
        return session != null ? session.getAttribute(name) : null;
    }

    /**
     * 获取session 属性
     *
     * @param <T> 返回对象的类型
     * @param name 属性名称
     * @param type 对象类型
     * @return 属性值；session 不存在时返回null；session中没有该熟属性时返回null
     */
    @SuppressWarnings("unchecked")
    public <T> T getSessionAttribute(String name, Class<T> type) {
        return (T) getSessionAttribute(name);
    }

    /**
     * 获取session属性列表
     *
     * @param <T> 返回对象的类型
     * @param name 属性名称
     * @param type 对象类型
     * @return 属性值列表；session 不存在时返回null；session中没有该熟属性时返回null
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getSessionAttributes(String name, Class<T> type) {
        return (List<T>) getSessionAttribute(name);
    }

    /**
     * 获取Web工程容器路径<br>
     * 如果部署为ROOT将返回空字符串, 否则返回斜杠加工程名; 如工程名为cheetah-site-web, 返回/cheetah-site-web
     *
     * @return context path
     */
    public String getContextPath() {
        return getRequest().getContextPath();
    }

    /**
     * 获取应用根目录
     *
     * @return root path
     */
    public String getWebRoot() {
        return getRequest().getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获取相对于根目录的路径
     *
     * @param path 相对路径
     * @return root path
     */
    public String getWebRoot(String path) {
        return getRequest().getSession().getServletContext().getRealPath("/") + path;
    }

    /**
     * 获取IP地址
     *
     * @author 龚健
     * @return
     */
    public String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 判断是否是微软公司发布的浏览器
     *
     * @param request http请求
     * @return 判断结果
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        final String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置下载文件时浏览器保存对话框显示的文件名
     *
     * @param request http请求
     * @param response http响应
     * @param fileName 浏览器保存对话框显示的文件名
     * @throws UnsupportedEncodingException
     */
    public static void setFileNameForDownload(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        // 解决文件下载时，文件名中文乱码问题
        if (WebUtil.isMSBrowser(request)) {
            String encodeFilename = URLEncoder.encode(fileName, "UTF-8");
            encodeFilename = encodeFilename.replace("+", "%20"); // 解决文件名称中有空格转码后有“+”号的问题
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", encodeFilename));
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=%s;filename*=UTF-8''%s", fileName, URLEncoder.encode(fileName, "UTF-8")));
        }
    }

    /**
     * 导出Excel文件
     *
     * @param excelFileName Excel文件名
     * @param pagingQueryService 分页查询服务
     * @param pagingParam 分页查询参数
     * @param exportBeanClass 导出数据的javabean
     * @param request http请求
     * @param response http响应
     * @param <T> 分页查询返回数据的泛型参数
     * @param <P> 分页查询参数的泛型参数
     * @param <E> 导出数据javabean的泛型参数
     * @throws Exception 方法内部可能抛出的异常
     */
    public static <T, P extends PagingParam, E> void exportExcelFile(String excelFileName,
                                                                     PagingQueryService<T, P> pagingQueryService,
                                                                     P pagingParam,
                                                                     Class<E> exportBeanClass,
                                                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        exportExcelFile(excelFileName, pagingQueryService, pagingParam, t -> {
            E exportBean = null;
            try {
                exportBean = exportBeanClass.getConstructor().newInstance();
                BeanUtils.copyProperties(t, exportBean);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("导出Excel文件数据转换时发生异常", e);
            }
            return exportBean;
        }, exportBeanClass, request, response);
    }

    /**
     * 导出Excel文件
     *
     * @param excelFileName Excel文件名
     * @param pagingQueryService 分页查询服务
     * @param pagingParam 分页查询参数
     * @param mapFunc 用于转换数据类型的函数式接口
     * @param exportBeanClass 导出数据的javabean
     * @param request http请求
     * @param response http响应
     * @param <T> 分页查询返回数据的泛型参数
     * @param <P> 分页查询参数的泛型参数
     * @param <E> 导出数据javabean的泛型参数
     * @throws Exception 方法内部可能抛出的异常
     */
    public static <T, P extends PagingParam, E> void exportExcelFile(String excelFileName,
                                                                     PagingQueryService<T, P> pagingQueryService,
                                                                     P pagingParam,
                                                                     Function<T, E> mapFunc,
                                                                     Class<E> exportBeanClass,
                                                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExcelExport excelExport = new ExcelExport(exportBeanClass, "");
        SXSSFWorkbook workbook = (SXSSFWorkbook) excelExport.getWorkBook();
        workbook.setCompressTempFiles(true); // 开启压缩临时文件，减少磁盘空间占用
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 设置导出excel文件时浏览器保存对话框显示的文件名
        WebUtil.setFileNameForDownload(request, response, excelFileName);
        boolean hasNextPage;
        int pageNum = 1;
        pagingParam.setPageNum(pageNum);

        do {
            PageInfo<T> pageInfo = pagingQueryService.pagingQueryList(pagingParam);
            String sheetName = String.format("第%d页", pageNum);
            // 新建sheet表，以及设置标题和列头名称和样式等等
            excelExport.createSheet(sheetName, exportBeanClass, ExcelField.Type.EXPORT);
            // 从数据库查询出来的数据转换成导出EXCEL用的数据
            List<E> dataList = pageInfo.getList().parallelStream().map(mapFunc).collect(Collectors.toList());
            // 在当前sheet中填充数据
            excelExport.setDataList(dataList, ExcelField.Type.EXPORT, exportBeanClass);
            hasNextPage = pageInfo.isHasNextPage();
            if (hasNextPage) {
                pageNum++;
                pagingParam.setPageNum(pageNum);
            }
        } while (hasNextPage);

        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream); // 把excel内容写入客户端
            outputStream.flush();
        } catch (IOException e) {
            log.error("Excel文件输出客户端时发生异常", e);
            throw new RuntimeException(e);
        } finally {
            // 清理临时文件
            workbook.dispose();
        }
    }

    /**
     * 下载Excel导入文件模板
     *
     * @param excelFileName Excel文件名
     * @param importBeanClass 定义导入数据的javabean
     * @param request http请求
     * @param response http响应
     * @param <T> 导入数据的javabean的类型参数
     * @throws Exception 方法内部可能抛出的异常
     */
    public static <T> void downloadExcelTemplate(String excelFileName, Class<T> importBeanClass,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExcelExport excelExport = new ExcelExport(importBeanClass, "");
        SXSSFWorkbook workbook = (SXSSFWorkbook) excelExport.getWorkBook();
        workbook.setCompressTempFiles(true); // 开启压缩临时文件，减少磁盘空间占用
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 设置导出excel文件时浏览器保存对话框显示的文件名
        WebUtil.setFileNameForDownload(request, response, excelFileName);
        excelExport.createSheet("", importBeanClass, ExcelField.Type.IMPORT);
        excelExport.setDataList(null, ExcelField.Type.IMPORT, importBeanClass);

        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream); // 把excel内容写入客户端
            outputStream.flush();
        } catch (IOException e) {
            log.error("Excel文件输出客户端时发生异常", e);
            throw new RuntimeException(e);
        } finally {
            // 清理临时文件
            workbook.dispose();
        }
    }
}
