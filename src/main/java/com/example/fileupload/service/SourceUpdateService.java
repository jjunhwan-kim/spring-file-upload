package com.example.fileupload.service;

import com.example.fileupload.model.Source;
import com.example.fileupload.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SourceUpdateService {

    private final SourceRepository sourceRepository;

    @Transactional
    public void updateSourceStatus(Source source) {
        log.info("updateSourceStatus({}) Started", source);

        Source foundSource = sourceRepository.findById(source.getId()).orElseThrow();
        foundSource.setStatus(source.getStatus());

        log.info("updateSourceStatus({}) Ended", source);
    }
}
