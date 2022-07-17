package com.example.fileupload.controller;

import com.example.fileupload.service.FileService;
import com.example.fileupload.service.SourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
public class FileUploadController {

    @Value("${path.upload}")
    private String uploadPath;

    private final SourceService sourceService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        log.info("uploadFile({}) Started", multipartFile);

        String originalFilename = multipartFile.getOriginalFilename();

        // Copy MultipartFile to local directory

        String uuid = UUID.randomUUID().toString();

        File root = new File(uploadPath + File.separator + uuid + File.separator + "temp");
        root.mkdirs();

        int index = originalFilename.lastIndexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException("File extension is not exists");
        }

        String fileName = originalFilename.substring(0, index);

        File source = new File(root.getPath() + File.separator + originalFilename);

        try {
            multipartFile.transferTo(source);
        } catch (IOException e) {
            throw new RuntimeException("File copy failed", e);
        }

        String destinationPath = uploadPath + File.separator + uuid;
        File destination = new File(destinationPath);

        sourceService.createSource(source, destination);

        log.info("uploadFile({}) Ended", multipartFile);
        return "ok";
    }
}
