package com.hunter.modulebaselib.bean;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public class ResultBean<T> {

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}