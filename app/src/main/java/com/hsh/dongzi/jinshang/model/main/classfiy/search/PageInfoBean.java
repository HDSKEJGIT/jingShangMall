package com.hsh.dongzi.jinshang.model.main.classfiy.search;

import java.io.Serializable;
import java.util.List;

public class PageInfoBean implements Serializable {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private int totalRows;
    private List<ResultSearchBean> list;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<ResultSearchBean> getList() {
        return list;
    }

    public void setList(List<ResultSearchBean> list) {
        this.list = list;
    }
}
