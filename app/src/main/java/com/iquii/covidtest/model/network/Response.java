package com.iquii.covidtest.model.network;

import java.time.temporal.ValueRange;

public class Response {

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private int status;
    private String body;

    public Response(int status, String body) {
        this.status = status;
        this.body = body;
    }

     public boolean isSuccesful() {
        return ValueRange.of(200,209).isValidIntValue(status);
    }




}
