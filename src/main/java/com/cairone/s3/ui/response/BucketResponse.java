package com.cairone.s3.ui.response;

import java.util.List;

public class BucketResponse {

    private List<String> names;

    public BucketResponse(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return this.names;
    }
}
