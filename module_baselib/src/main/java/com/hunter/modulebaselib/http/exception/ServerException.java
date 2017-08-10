package com.hunter.modulebaselib.http.exception;

/**
 * Created by Administrator on 2017/8/10.
 */

public class ServerException extends RuntimeException {
    private int code;
    private String message;

    public ServerException(String message, int code) {
        this.message = message;
        this.code = code;
    }
    public ServerException(String message) {
        this.message = message;
    }
}
