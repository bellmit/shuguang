package com.sofn.core.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * json 工具类
 * <pre>职责: 后台json数据输出
 * 使用方法：
 * </pre>
 * @see #
 * @author qjh 2016年11月1日 上午10:31:44
 * @since
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	/**
	 * <pre>功能描述: 后台数据以json方式输出到前台
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param response
	 * @param responseJson
	 * @see #
	 * @since
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static void responseJson(HttpServletResponse response, ResponseJson responseJson) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(responseJson.toJsonString());
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally{
			if (out != null) {  
	            out.close();  
	        } 
		}
	}
	
	public static void responseJson(HttpServletResponse response, String jsonStr) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(jsonStr);
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally{
			if (out != null) {  
	            out.close();  
	        } 
		}
	}
	
	/**
	 * 
	 * <pre>功能描述: ajax跨域请求jsonp
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param request
	 * @param response
	 * @param responseJson
	 * @see #
	 * @since
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static void responseJsonp(HttpServletRequest request, HttpServletResponse response,
			ResponseJson responseJson) {
		String callback = request.getParameter("callback");
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (StringUtils.isEmpty(callback)) {
				out.write(responseJson.toJsonString());
			}else {
				out.write(callback + "(" + responseJson.toJsonString() + ")");
			}
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	/**
	 * 
	 * <pre>功能描述:后台数据以html方式输出到前台,如用上面的方法上传会出现下载提示框,若用下面的方法是不会出现下载框 
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param response
	 * @param responseJson
	 * @see #
	 * @since
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static void responseJsonByUpload(HttpServletResponse response, ResponseJson responseJson) {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(responseJson.toJsonString());
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally{
			if (out != null) {  
	            out.close();  
	        } 
		}
	}
}
