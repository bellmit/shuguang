/**
 *
 */
package com.sofn.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sofn.core.Constants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.BaseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.OpenOfficeUtil;
import com.sofn.core.util.WebUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * 控制器基类
 *
 * @author sofn
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前用户Id
     */
    protected String getCurrUser() {
        return WebUtil.getCurrentUserId();
    }

    /**
     * 设置成功响应代码
     */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    /**
     * 设置成功响应代码
     */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    /**
     * 设置权限失败响应代码
     */
    protected ResponseEntity<ModelMap> setFailModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.BAD_REQUEST, data);
    }


    /**
     * 设置响应代码
     */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, String message, Object data) {
        if (modelMap == null) {
            modelMap = new ModelMap();
        }
        modelMap.remove("void");
        if (data != null) {
            modelMap.put("data", data);
        } else if (!modelMap.containsKey("data")) {
            modelMap.put("data", null);
        }
        modelMap.put("httpCode", code.value());
        if (message != null) {
            modelMap.put("msg", message);
        } else {
            modelMap.put("msg", code.msg());
        }

        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    /**
     * 设置响应代码
     */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        if (modelMap == null) {
            modelMap = new ModelMap();
        }
        modelMap.remove("void");
        if (data != null) {
            modelMap.put("data", data);
        } else if (!modelMap.containsKey("data")) {
            modelMap.put("data", null);
        }
        modelMap.put("httpCode", code.value());
        modelMap.put("msg", code.msg());
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    protected Map<String, Object> setResultMap(HttpCode code, String message, Object data) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        if (data != null) {
            resultMap.put("data", data);
        } else if (!resultMap.containsKey("data")) {
            resultMap.put("data", null);
        }
        resultMap.put("httpCode", code.value());
        if (message != null) {
            resultMap.put("msg", message);
        } else {
            resultMap.put("msg", code.msg());
        }

        resultMap.put("timestamp", System.currentTimeMillis());
        return resultMap;
    }

    protected Map<String, Object> setResultMap(HttpCode code, Object data) {
        return setResultMap(code, null, data);
    }

    protected Map<String, Object> setResultMap(HttpCode code) {
        return setResultMap(code, null);
    }

    protected Map<String, Object> setResultMap(HttpCode code, String message) {
        return setResultMap(code, message, null);
    }

    protected Map<String, Object> setSuccessResultMap(Object data) {
        return setResultMap(HttpCode.OK, null, data);
    }

    /**
     * 设置权限判断响应代码
     */
    protected ResponseEntity<ModelMap> setLimitModelMap(ModelMap modelMap, HttpServletRequest request, Object data) {

        Object code = request.getAttribute("code");

        if (modelMap == null) {
            modelMap = new ModelMap();
        }
        modelMap.remove("void");
        if (data != null) {
            modelMap.put("data", data);
        } else if (!modelMap.containsKey("data")) {
            modelMap.put("data", null);
        }

        if (!(String.valueOf(ApiMsgConstants.SUCCESS_CODE)).equals(code.toString())) {

            modelMap.put("httpCode", ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            modelMap.put("msg", ApiMsgConstants.TOKEN_ILLEGAL);
            modelMap.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(modelMap);
        } else {
            modelMap.put("httpCode", code);
            modelMap.put("msg", ApiMsgConstants.SUCCESS_MSG);
            modelMap.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(modelMap);
        }

    }

    /**
     * 异常处理
     */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        logger.error(Constants.Exception_Head, ex);
        ModelMap modelMap = new ModelMap();

        if (ex instanceof BaseException) {
            ((BaseException) ex).handler(modelMap);
        } else if (ex instanceof IllegalArgumentException) {
            new IllegalParameterException(ex.getMessage()).handler(modelMap);
        } else if (ex instanceof UnauthorizedException) {
            setModelMap(modelMap, HttpCode.FORBIDDEN);
        } else if (ex instanceof BindException) {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            List<ObjectError> errorList = ((BindException) ex).getAllErrors();
            processValidationErrorMessage(modelMap, errorList);
        } else if (ex.getCause() instanceof BindException) {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            BindException bindException = (BindException) ex.getCause();
            List<ObjectError> errorList = bindException.getAllErrors();
            processValidationErrorMessage(modelMap, errorList);
        } else if (ex instanceof MethodArgumentNotValidException) {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();

            List<ObjectError> errorList = bindingResult.getAllErrors();
            processValidationErrorMessage(modelMap, errorList);
        } else {
            Map<String, String> exceptionInfo = new HashMap<>();
            exceptionInfo.put("message", ex.getMessage());
            exceptionInfo.put("causedBy", ex.getCause() == null ? "" : ex.getCause().getMessage());
            modelMap.put("exception", exceptionInfo);
            setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        request.setAttribute("msg", modelMap.get("msg"));
        response.setContentType("application/json;charset=UTF-8");
        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }

    private void processValidationErrorMessage(ModelMap modelMap, List<ObjectError> errorList) {
        StringBuilder errorMessages = new StringBuilder(errorList.size());
        errorMessages.append(" ( ");

        for (ObjectError error : errorList) {
            if (error instanceof FieldError) {
                errorMessages.append(((FieldError) error).getField());
            } else {
                errorMessages.append(error.getObjectName());
            }
            errorMessages.append(": ").append(error.getDefaultMessage()).append("; ");
        }
        errorMessages.append(")");
        modelMap.put("msg", modelMap.get("msg") + errorMessages.toString());
    }
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String uploadFile1(File file) throws Exception {
        return FastDFSUtil.getUpliadFileAddress(file);
    }

    /**
     * 文件上传
     */
    public String uploadFile(HttpServletRequest request) throws Exception {
        String address = null;
        //将当前上下文初始化
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest中所有的文件名
            Iterator iter = multipartRequest.getFileNames();
            String path = request.getSession().getServletContext().getRealPath("upload");
            while (iter.hasNext()) {
                //一次便利所有文件
                MultipartFile file = multipartRequest.getFile(iter.next().toString());
                address = FastDFSUtil.getUpliadFileAddress2(file);
            }
        }
        return address;
    }

    /**
     * 文件转PDF（）
     *
     * @param
     * @return
     */
    public File word2PDF(HttpServletRequest request) throws Exception {
        File result = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            String path2 = request.getSession().getServletContext().getRealPath("upload");

            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());

                File file2 = new File(path2);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                String filename = file.getOriginalFilename();
                String filenam2 = filename.replace("docx", "doc");
                File file3 = new File(file2, filenam2);
                file.transferTo(file3);
                File inputfile = new File(path2, "/sofn.pdf");
                if (!inputfile.exists()) {
                    inputfile.createNewFile();
                }
                if (OpenOfficeUtil.doc2pdf(file3, inputfile)) {
                    result = inputfile;
                    file3.delete();
                }
            }
        }
        return result;
    }
}
