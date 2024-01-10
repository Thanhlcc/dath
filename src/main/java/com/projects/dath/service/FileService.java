package com.projects.dath.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String store(MultipartFile file) throws IOException;
    boolean remove(String fileName);
    boolean isExist(String fileName);
    boolean removeAll();
}
