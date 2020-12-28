package com.example.crudproject.dto;

import java.util.Date;
import java.util.List;

public class QueryResult<T> {
    private T t;

    private List<Object[]> datas;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<Object[]> getDatas() {
        return datas;
    }

    public void setDatas(List<Object[]> datas) {
        this.datas = datas;
    }

    public QueryResult() {
    }

    public QueryResult(T t) {
        this.t = t;
    }
}