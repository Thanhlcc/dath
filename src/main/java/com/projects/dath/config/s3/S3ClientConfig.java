package com.projects.dath.config.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3ClientConfig {
    private final S3Properties properties;
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(properties.region())) // where your bucket resides
                .forcePathStyle(true)
                .build();
    }
}
