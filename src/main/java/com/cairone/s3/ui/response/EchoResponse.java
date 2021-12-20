package com.cairone.s3.ui.response;

public class EchoResponse {

    private String response;

    public  EchoResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
