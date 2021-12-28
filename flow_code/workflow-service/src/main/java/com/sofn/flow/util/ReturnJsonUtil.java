package com.sofn.flow.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author heyongjie
 * @date 2020/5/7 13:47
 */
public class ReturnJsonUtil {

    /**
     * 将查询结果返回json字符串
     * @param response   HttpServletResponse
     */
    public static void returnJson(HttpServletResponse response,Object obj)  {
        response.setCharacterEncoding("utf-8");
        TokenUtil.remove();
        String result = JsonUtils.obj2json(obj);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
