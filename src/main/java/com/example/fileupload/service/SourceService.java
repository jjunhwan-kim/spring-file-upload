package com.example.fileupload.service;

import com.example.fileupload.model.Source;
import com.example.fileupload.model.SourceStatus;
import com.example.fileupload.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SourceService {

    private final SourceRepository sourceRepository;
    private final FileService fileService;

    @Transactional
    public void createSource(File source, File destination) {
        log.info("createSource({}, {}) Started", source, destination);

        Source s = new Source();
        s.setDownloadPath(source.getPath());
        s.setAnalyzePath(destination.getPath());
        s.setStatus(SourceStatus.READY);
        sourceRepository.save(s);

        fileService.unzipSource(s);

        log.info("createSource({}, {}) Ended", source, destination);
    }
}
