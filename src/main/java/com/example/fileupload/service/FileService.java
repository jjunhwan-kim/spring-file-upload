package com.example.fileupload.service;

import com.example.fileupload.model.Source;
import com.example.fileupload.model.SourceStatus;
import com.example.fileupload.repository.SourceRepository;
import com.example.fileupload.util.ZipUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final SourceUpdateService sourceUpdateService;
    private final ZipUtils zipUtils;

    @Async
    public void unzipSource(Source source) {
        log.info("unzipSource({}) Started", source);


        String downloadPath = source.getDownloadPath();
        String analyzePath = source.getAnalyzePath();

        try {
            zipUtils.unzipFile(downloadPath, analyzePath);
            source.setStatus(SourceStatus.SUCCESS);
        } catch (RuntimeException e) {
            log.info("Unzip Failed", e);
            source.setStatus(SourceStatus.FAIL);
        }

        sourceUpdateService.updateSourceStatus(source);

        log.info("unzipSource({}) Ended", source);
    }
}
