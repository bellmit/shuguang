package com.sofn.sys.persistence;


import java.io.Serializable;


/**
 * 共享系统单独提出来的page，之前老项目的page依赖东西太多
 */
public class Page implements Serializable{
    /**
     * 设置的默认值
     */
    public static final long DEFAULT_PAGE_SIZE = 3;
    private static final long serialVersionUID = 6162557124413881609L;

    private long pageOffset ;
    private long pageTail ;
    private long pageCount ; // 总页数

    // 安全验证 用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。
    // 要求在服务器接收到此参数后再返回
    private long draw;

    /**
     * recordsTotal : 结果总数（dataTables的分页参数）
     */
    private long recordsTotal;

    /**
     * recordsFiltered 条件过滤后记录数 必要 （dataTables的分页参数）
     */
    private long recordsFiltered;

    /**
     * start :记录开始处（dataTables的分页参数）
     */
    private Long start;
    /**
     * length :每页记录数（dataTables的分页参数）
     */
    private Long length;

    private Long pageSize;

    private Long pageNumber;

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public long getPageTail() {
        return pageTail;
    }

    public void setPageTail(long pageTail) {
        this.pageTail = pageTail;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Page() {
    }

    // 计算分页参数
    public void doPage(){
        if(this.length <= 0L){
            this.length =DEFAULT_PAGE_SIZE;
        }
        if(this.start <= 0L){
            this.start = 0L;
        }
        if(this.recordsTotal < 0){
            this.recordsFiltered = 0;
        }
        this.pageCount = (this.recordsTotal - 1) / this.length + 1;
        this.pageOffset = this.start + 1;
        this.pageTail = this.pageOffset + this.length - 1;
        if ((this.pageOffset + this.length) > this.recordsTotal) {
            this.pageTail = this.recordsTotal;
        }

        // 计算 pageSize 和 pageNumber
        this.pageNumber = 1L;
        this.pageSize = 10L;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("pageOffset=").append(pageOffset);
        sb.append(", pageTail=").append(pageTail);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", draw=").append(draw);
        sb.append(", recordsTotal=").append(recordsTotal);
        sb.append(", recordsFiltered=").append(recordsFiltered);
        sb.append(", start=").append(start);
        sb.append(", length=").append(length);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageNumber=").append(pageNumber);
        sb.append('}');
        return sb.toString();
    }
}
