package com.cairone.s3.ui.ctrl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.cairone.s3.ui.response.BucketResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("api/buckets")
@Produces(MediaType.APPLICATION_JSON)
public class BucketCtrl {

    private final AmazonS3 client;

    @Inject
    public BucketCtrl(AmazonS3 client) {
        this.client = client;
    }

    @GET
    public BucketResponse list() {
        List<String> names = client.listBuckets().stream()
            .map(Bucket::getName)
            .collect(Collectors.toList());
        return new BucketResponse(names);
    }
}
