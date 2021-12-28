package com.sofn.core.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 后台与前台交互json对象
 * 
 * @author qjh  2016年11月1日
 *
 */
public class ResponseJson {
	/**
	 * 是否成功，默认true
	 */
	private boolean success = true;
	/**
	 * 提示信息，默认"操作成功"
	 */
	private String msg = "操作成功";
	/**
	 * 记录总数，分页时使用
	 */
	private int total;
	/**
	 * 当前页码，这个属性最好别使用
	 */
	private int pageNumber;
	/**
	 * 记录数据列表
	 */
	private List<?> rows = new ArrayList<>();
	/**
	 * 其他的信息，最好也别使用，可以直接将信息放在attributes中
	 */
	private Object obj = null;
	/**
	 * 存放其它数据的容器
	 */
	private Map<String, Object> attributes = new HashMap<>();
	
	/**
	 * 是否需要将null对象输出为某种字符串（默认为空字符串），默认为false，不转换
	 */
	@JSONField(serialize=false)
	private boolean isWriteNullToSomeString = false;
	
	/**
	 * 将null转换为何种字符串，默认为空字符串（""）
	 */
	@JSONField(serialize=false)
	private String nullToString = "";
	
	/**
	 * 构建对象，默认success为true
	 */
	public ResponseJson(){
		
	}
	
	/**
	 * 构建对象
	 * @param success 是否成功
	 */
	public ResponseJson(boolean success){
		this.success= success;
	}
	
	/**
	 * 构建对象
	 * @param success 是否成功
	 * @param isWriteNullToSomeString 是否需要将null对象输出为某种字符串（默认为空字符串）
	 */
	public ResponseJson(boolean success, boolean isWriteNullToSomeString){
		this.success = success;
		this.isWriteNullToSomeString = isWriteNullToSomeString;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getTotal() {
		return total;
	}
	/**
	 * 设置记录总数
	 * @param total 总数量
	 * @since
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	/**
	 * 获取额外数据存放map
	 * @return
	 * @since
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public String getMsg() {
		return msg;
	}
	/**
	 * 设置消息信息
	 * @param msg 消息信息
	 * @since
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public boolean isWriteNullToSomeString() {
		return isWriteNullToSomeString;
	}
	
	/**
	 * 设置是否将空对象输出为某种字符串(默认为"")
	 * @param isWriteNullToSomeString true为转换，false不转换，默认为false
	 */
	public void setWriteNullToSomeString(boolean isWriteNullToSomeString) {
		this.isWriteNullToSomeString = isWriteNullToSomeString;
	}	
	

	public String getNullToString() {
		return nullToString;
	}

	/**
	 * 将null对象转换为何种字符串<br/>
	 * 此方法会将isWriteNullToSomeString改变为true
	 * @param nullToString 将null对象转换成的字符串(参数为空时，设置无效)
	 */
	public void setNullToString(String nullToString) {
		if(nullToString != null){
			this.isWriteNullToSomeString = true;
			this.nullToString = nullToString;
		}
	}

	/**
	 * 
	 * <pre>功能描述：转换为json字符串，可调用addFilter和addFeature添加过滤和格式化
	 * 使用方法：
	  	SimplePropertyPreFilter organizationFilter = new SimplePropertyPreFilter(Organization.class);
	 	organizationFilter.getExcludes().add("children");
	 	this.getFilters().add(organizationFilter);
	 
	 	SimplePropertyPreFilter roleFilter = new SimplePropertyPreFilter(Role.class);
	 	roleFilter.getExcludes().add("userRoleAuthorizes");
	 	roleFilter.getExcludes().add("roleMenuAuthorizes");
		this.getFilters().add(roleFilter);
		
		this.getFeatures().add(SerializerFeature.PrettyFormat);
		
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @return
	 * @since 1.1.0
	 * 修改记录（修改时间，修改人、修改内容、修改原因）
	 * </pre>
	 */
	public String toJsonString(){
		
		if(isWriteNullToSomeString){
			filters.add(new ValueFilter() {
				
				@Override
				public Object process(Object object, String name, Object value) {
					return value == null ? nullToString : value;
				}
			});
		}
		
		String result = JSON.toJSONString(this, 
				filters.toArray(new SerializeFilter[0]), 
				features.toArray(new SerializerFeature[0]));
		
		return result;
	}
	
	/**
	 * 转换过滤规则集合
	 */
	private transient List<SerializeFilter> filters = new ArrayList<>(0);
	/**
	 * 转换特征规则集合
	 */
	private transient List<SerializerFeature> features = new ArrayList<>(0);

	/**
	 * 
	 * <pre>功能描述：获取SerializeFilter集合，可往里面添加新的SerializeFilter
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @return
	 * @since
	 * 修改记录（修改时间，修改人、修改内容、修改原因）
	 * </pre>
	 */
	public List<SerializeFilter> getFilters() {
		return filters;
	}

	/**
	 * 
	 * <pre>功能描述：设置SerializeFilter集合，如果参数filters为空，则不进行任何操作
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param filters
	 * @since
	 * 修改记录（修改时间，修改人、修改内容、修改原因）
	 * </pre>
	 */
	public void setFilters(List<SerializeFilter> filters) {
		if(filters != null){
			this.filters = filters;
		}
	}

	/**
	 * 
	 * <pre>功能描述：获取SerializerFeature集合，可往里面添加新的SerializerFeature
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @return
	 * @since
	 * 修改记录（修改时间，修改人、修改内容、修改原因）
	 * </pre>
	 */
	public List<SerializerFeature> getFeatures() {
		return features;
	}

	/**
	 * 
	 * <pre>功能描述：设置SerializerFeature集合，如果参数features为空，则不进行任何操作
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param features
	 * @since
	 * 修改记录（修改时间，修改人、修改内容、修改原因）
	 * </pre>
	 */
	public void setFeatures(List<SerializerFeature> features) {
		if(features != null){
			this.features = features;
		}
	}

}
