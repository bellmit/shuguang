package com.sofn.core.persistence;

import com.sofn.core.constant.ApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-10-17.
 * Time: 22:15.
 * Description: 分页封装类
 */
public class Pager implements Serializable {
    private static final long serialVersionUID = -8773454829579892901L;
    // 当前页码
    private Integer pageNumber = 1;
    // 总行数
    private Integer recordsTotal = 0;
    // 默认页大小
    private Integer pageSize = ApiConstants.DEFUALUT_PAGESIZE;
    // 总页数
    private Integer pageCount = 0;
    // 当前页起始记录
    private Integer pageOffset = 0;
    // 当前页到达的记录
    private Integer pageTail = 0;
    // 当前分页的集合
    private List<?> list;

    public Pager(){

    }
    public Pager(HttpServletRequest request) {
        String pageNumber = WebUtils.findParameterValue(request, "pageNumber");
        String pageSize = WebUtils.findParameterValue(request, "pageSize");
        this.pageNumber=(pageNumber == null ? 1 : Integer.parseInt(pageNumber));
        this.pageSize=(pageSize == null ? this.pageSize : Integer.parseInt(pageSize));
    }

    public Pager(int pageSize, int pageNumber) {
        super();
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
    /**
     * 初始化分页数据,根据分页大小和记录总数计算总页数
     */
    public void doPage() {
        this.pageCount = (this.recordsTotal - 1) / this.pageSize + 1;
        this.pageOffset = (this.pageNumber - 1) * this.pageSize;
        this.pageTail = this.pageOffset + this.pageSize;
        if ((this.pageOffset + this.pageSize) > this.recordsTotal) {
            this.pageTail = this.recordsTotal;
        }
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<?> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(List<?> list) {
        this.list = list;
    }

    /**
     * 返回pageNumber属性
     *
     * @return pageNumber属性 page no
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置pageNumber属性
     *
     * @param pageNumber pageNumber属性
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = (pageNumber == null || pageNumber == 0 ? 1 : pageNumber);
    }

    /**
     * 返回recordsTotal属性
     *
     * @return recordsTotal属性 row count
     */
    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * 设置recordsTotal属性
     *
     * @param recordsTotal recordsTotal属性
     */
    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * 返回pageSize属性
     *
     * @return pageSize属性 page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性
     *
     * @param pageSize pageSize属性
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = (pageSize == null || pageSize == 0 ? 10 : pageSize);
    }

    /**
     * 返回pageCount属性
     *
     * @return pageCount属性 page count
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 设置pageCount属性
     *
     * @param pageCount pageCount属性
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 返回pageOffset属性
     *
     * @return pageOffset属性 page offset
     */
    public Integer getPageOffset() {
        return pageOffset;
    }

    /**
     * 设置pageOffset属性
     *
     * @param pageOffset pageOffset属性
     */
    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    /**
     * 返回pageTail属性
     *
     * @return pageTail属性 page tail
     */
    public Integer getPageTail() {
        return pageTail;
    }

    /**
     * 设置pageTail属性
     *
     * @param pageTail pageTail属性
     */
    public void setPageTail(Integer pageTail) {
        this.pageTail = pageTail;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}


