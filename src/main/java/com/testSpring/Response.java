package com.testSpring;

public class Response {
    private final String status;
    private final int code;

    public Response(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }
}
