package com.cairone.s3.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(SpringExtension.class)
public class BucketServiceTest {

    public static final String AWS_BUCKET_NAME = "testing-bucket";

    @DynamicPropertySource
    static void overrideConfiguration(DynamicPropertyRegistry registry) {
        //registry.add("cloud.aws.credentials.access-key", localStack::getAccessKey);
        //registry.add("cloud.aws.credentials.secret-key", localStack::getSecretKey);
    }

    @TestConfiguration
    static class AwsTestConfig {

        @Bean
        public AmazonS3 amazonS3() {
            //0.11.3
            LocalStackContainer localStack =
                new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.13.0"))
                    .withServices(Service.S3);
            localStack.start();

            AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withCredentials(localStack.getDefaultCredentialsProvider())
                .withEndpointConfiguration(localStack.getEndpointConfiguration(Service.S3))
                .build();
            client.createBucket(AWS_BUCKET_NAME);

            return client;
        }

        @Bean
        public BucketService getBucketService() {
            return new BucketService(amazonS3());
        }
    }

    @Inject
    private BucketService bucketService;

    @Test
    public void test() {
        assertThat(bucketService).isNotNull();
        assertThat(bucketService.list()).isNotNull();

        final List<String> names = bucketService.list().getNames();
        assertThat(names).isNotEmpty();
        assertThat(names).contains(AWS_BUCKET_NAME);
    }
}
