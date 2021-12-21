package com.cairone.s3.ui.ctrl;

import com.cairone.s3.service.BucketService;
import com.cairone.s3.ui.response.BucketResponse;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api/buckets")
@Produces(MediaType.APPLICATION_JSON)
public class BucketCtrl {

    private final BucketService bucketService;

    @Inject
    public BucketCtrl(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GET
    public BucketResponse list() {
        return bucketService.list();
    }
}
