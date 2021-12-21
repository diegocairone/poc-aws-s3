package com.cairone.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.cairone.s3.ui.response.BucketResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BucketService {

    private final AmazonS3 client;

    @Inject
    public BucketService(AmazonS3 client) {
        this.client = client;
    }

    public BucketResponse list() {
        List<String> names = client.listBuckets().stream()
            .map(Bucket::getName)
            .collect(Collectors.toList());
        return new BucketResponse(names);
    }
}
