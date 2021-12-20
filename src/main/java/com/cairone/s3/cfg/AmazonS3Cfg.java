package com.cairone.s3.cfg;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Cfg {

    @Bean
    public AmazonS3 getAmazonS3() {
        return AmazonS3ClientBuilder
            .standard()
            //.withCredentials(getAWSStaticCredentialsProvider())
            //.withRegion(Regions.SA_EAST_1)
            .build();
    }

    private AWSStaticCredentialsProvider getAWSStaticCredentialsProvider() {
        AWSCredentials credentials = new BasicAWSCredentials(
            "", "");
        return new AWSStaticCredentialsProvider(credentials);
    }
}
