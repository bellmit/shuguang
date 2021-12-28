package com.sofn.core.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/** 
 *  
 * 模板工具类 
 */  
public class FreeMarkerUtil {
	
	private String templatePath = "";
	
	private String templateName = "";
	
	private String templateEncoding = "";
	
	public FreeMarkerUtil(){}
	
	
	/**
	 * 
	 * @param templatePath 模版路径
	 * @param templateName 模版名称
	 */
	public FreeMarkerUtil(String templatePath,String templateName){
		this.templatePath = templatePath;
		this.templateName = templateName;
		this.templateEncoding = "UTF-8";
	}
	
	/**
	 * 
	 * @param templatePath 模版路径
	 * @param templateName 模版名称
	 * @param templateEncoding 字符集
	 */
	public FreeMarkerUtil(String templatePath,String templateName,String templateEncoding){
		this.templatePath = templatePath;
		this.templateName = templateName;
		this.templateEncoding = templateEncoding;
	}
	
	/**
	 * 生成指定文件
	 * @param root 数据源
	 * @param out  流
	 * @param fileUrl 文件路径
	 * @param fileName 文件名称
	 */
	public void processTemplate(Map<String,Object> root,Writer out,String fileUrl,String fileName){
		 try {  
	            Configuration config=new Configuration();  
	            File file=new File(templatePath);  
	            //设置要解析的模板所在的目录，并加载模板文件  
	            config.setDirectoryForTemplateLoading(file);  
	            //设置包装器，并将对象包装为数据模型  
	            config.setObjectWrapper(new DefaultObjectWrapper());  
	            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
	            Template template=config.getTemplate(templateName,templateEncoding);  
	            //指定文件存放路径是否存在，不存在，自动创建
	            File furl = new File(fileUrl);
	            if (!furl.exists()) {
	            	furl.mkdirs();
	    		}
	            //要生成文件存放位置
	            File fileCode = new File(fileUrl+"/"+fileName);
	            //合并数据模型与模板  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileCode), "UTF-8"));  
	            template.process(root, out);
	            out.flush();  
	            out.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }catch (TemplateException e) {  
	            e.printStackTrace();  
	        }  
		
	}
	
	
	
	
	/**
	 * 
	 * @param root 模版数据
	 * @param templateObj 生成文件路径  eg:d:/123.html
	 * @param out 流文件
	 */
	public void processTemplate(Map<String,Object> root,String templateObj,Writer out){
		 try {  
	            Configuration config=new Configuration();  
	            File file=new File(templatePath);  
	            //设置要解析的模板所在的目录，并加载模板文件  
	            config.setDirectoryForTemplateLoading(file);  
	            //设置包装器，并将对象包装为数据模型  
	            config.setObjectWrapper(new DefaultObjectWrapper());  
	              
	            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
	            Template template=config.getTemplate(templateName,templateEncoding);  
	            //合并数据模型与模板  
	            File htmlFile = new File(templateObj);
	            if (!htmlFile.exists()) {
	            	htmlFile.mkdirs();
	    		}
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));  
	            template.process(root, out);
	            out.flush();  
	            out.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }catch (TemplateException e) {  
	            e.printStackTrace();  
	        }  
	}
	
	
	
	
    /** 
     * @param templatePath 模板文件存放目录  
     * @param templateName 模板文件名称  
     * @param root 数据模型根对象 
     * @param templateEncoding 模板文件的编码方式 
     * @param out 输出流 
     */  
    public static void processTemplate(String templatePath, String templateName, String templateEncoding, Map<?,?> root, Writer out){  
        try {  
            Configuration config=new Configuration();  
            File file=new File(templatePath);  
            //设置要解析的模板所在的目录，并加载模板文件  
            config.setDirectoryForTemplateLoading(file);  
            //设置包装器，并将对象包装为数据模型  
            config.setObjectWrapper(new DefaultObjectWrapper());  
              
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
            Template template=config.getTemplate(templateName,templateEncoding);  
            //合并数据模型与模板  
            String htmlPath = "d:/123.html";  
            File htmlFile = new File(htmlPath);  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));  
            template.process(root, out);
            out.flush();  
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    public static boolean creatDirs(String aParentDir, String aSubDir)
    {
        File aFile = new File(aParentDir);
        if (aFile.exists())
        {
            File aSubFile = new File(aParentDir + aSubDir);
            if (!aSubFile.exists())
            {
                return aSubFile.mkdirs();
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

	/**
	 * 创建word文档
	 * @param dataMap 要填入模本的数据文件
	 * @param context ServletActionContext.getServletContext()
	 * @return 返回文档输出流
	 * @throws UnsupportedEncodingException
	 */
	public ByteArrayOutputStream processTemplate(Map<String,Object> dataMap, ServletContext context) throws UnsupportedEncodingException {
		//模版放在web工程下
		Configuration config=new Configuration();
		config.setServletContextForTemplateLoading(context, templatePath);
		Template t=null;
		try {
			//装载模板
			t = config.getTemplate(templateName,"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Writer out = null;
		ByteArrayOutputStream  os=null;
		os = new ByteArrayOutputStream();
		OutputStreamWriter oWriter = new OutputStreamWriter(os,"UTF-8");
		//这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
		//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		out = new BufferedWriter(oWriter);
		try {
			t.process(dataMap, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return os;
	}



} 