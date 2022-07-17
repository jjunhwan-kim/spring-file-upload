package com.example.fileupload.util;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ZipUtils {

    public void unzipFile(String source, String destination) {

        log.info("unzipFile({}, {}) Started", source, destination);

        ZipFile zipFile = new ZipFile(source);

        try {
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            log.info("Unzip file Error", e);
            throw new RuntimeException("Unzip file error occured");
        }

        log.info("unzipFile({}, {}) Ended", source, destination);
    }
}
