package com.projects.dath.config.s3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aws")
public record S3Properties(
    String bucketName,
    String region
){
}
