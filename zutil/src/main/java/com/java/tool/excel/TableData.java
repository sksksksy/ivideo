package com.java.tool.excel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TableData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> headers;
    private List<T> dataList;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setHeaders(String... headers) {
        this.headers = new LinkedList<String>();
        for (String e : headers) {
            this.headers.add(e);
        }
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(@SuppressWarnings("unchecked") T... dataLists) {
        this.dataList = new LinkedList<T>();
        for (T t : dataLists) {
            this.dataList.add(t);
        }
    }
}

