package com.projects.dath.service.impl;

import com.projects.dath.exception.StorageException;
import com.projects.dath.config.s3.S3Properties;
import com.projects.dath.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service implements FileService {
    private S3Client s3Client;
    private S3Properties properties;
    private String ctxPath;

    public S3Service(@Autowired S3Client s3Client, @Autowired S3Properties properties, String ctxPath) {
        this.ctxPath = ctxPath;
    }

    @Override
    public String store(MultipartFile file) throws IOException, S3Exception {
        if (file.isEmpty()) {
            throw new StorageException("File is empty");
        }
        String key = null;
        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(properties.bucketName())
                .key(key)
                .build();
        s3Client.putObject(putReq, RequestBody.fromBytes(file.getBytes()));
        return key;
    }

    @Override
    public boolean remove(String fileName) {
        try {
            DeleteObjectRequest deleteReq = DeleteObjectRequest.builder()
                    .bucket(properties.bucketName())
                    .key(ctxPath + '/' + fileName)
                    .build();
            s3Client.deleteObject(deleteReq);
            return true;
        } catch (S3Exception exception) {
            log.error(exception.getMessage());
            return false;
        }
    }

    public boolean removeAll() {
        return false;
    }
    @Override
    public boolean isExist(String fileName) {
        try {
            HeadObjectRequest req = HeadObjectRequest.builder()
                    .bucket(properties.bucketName())
                    .key( ctxPath + '/' + fileName)
                    .build();
            HeadObjectResponse res = s3Client.headObject(req);
            return true;
        } catch (S3Exception exception) {
            if(exception.statusCode() == HttpStatusCode.BAD_REQUEST){
                log.error("The requested object does not exist");
                return false;
            }
            throw exception;
        }
    }
}
