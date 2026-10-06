package ch.zhaw.avalanger.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import ch.zhaw.avalanger.repository.AvalangeRepository;
import lombok.RequiredArgsConstructor;

@Service
@EnableScheduling
@RequiredArgsConstructor 
public class ArchiveService {
    
    private final AvalangeRepository avalangeRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void archiveAllAvalanges() {
        var avalanges = avalangeRepository.findAll();
        avalanges.forEach(avalange -> avalange.setState(avalange.getState().ARCHIVED));
        avalangeRepository.saveAll(avalanges);
    }
}
