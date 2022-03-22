package com.vitvn183.shortedlink.services;

import com.vitvn183.shortedlink.entity.OriginalLink;
import com.vitvn183.shortedlink.exceptions.NotFoundException;
import com.vitvn183.shortedlink.model.dto.OriginalLinkDto;
import com.vitvn183.shortedlink.repositories.OriginalLinkRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OriginalLinkserviceImp implements OriginalLinkService {

    @Autowired
    private OriginalLinkRepository originalLinkRepository;

    @Override
    public List<OriginalLink> getAll() {
        return originalLinkRepository.findAll();
    }

    @Override
    public Optional<OriginalLink> getFindById(Long id) {
        return originalLinkRepository.findById(id);
    }

    @Override
    public void addNew(OriginalLinkDto originalLinkDto) {
        createOrUpdate(new OriginalLink(), originalLinkDto);
    }

    @Override
    public String getOriginalLink(String shortLink) {
        OriginalLink originalLink = originalLinkRepository.findOriginalLinkByShortLink(shortLink);
        return originalLink.getLink();
    }

    @Override
    public void update(Long id, OriginalLinkDto originalLinkDto) {
        Optional<OriginalLink> originalLink = originalLinkRepository.findById(id);
        if(originalLink.isEmpty()) {
            throw new NotFoundException("Original link id: " + id + " not found!");
        }
        createOrUpdate(originalLink.get(), originalLinkDto);

    }

    public void createOrUpdate(OriginalLink originalLink, OriginalLinkDto originalLinkDto) {
        originalLink.setLink(originalLinkDto.getLink());

        List<OriginalLink> originalLinks = originalLinkRepository.findAll();
        String randomStr = RandomStringUtils.randomAlphabetic(10);

        for (OriginalLink link : originalLinks) {
            if (link.getShortLink().equals(randomStr)) {
                randomStr = RandomStringUtils.randomAlphabetic(10);
            }
        }
            originalLink.setShortLink(randomStr);

        originalLinkRepository.save(originalLink);
    }

    @Override
    public void delete(Long id) {
        Optional<OriginalLink> originalLink = originalLinkRepository.findById(id);
        if(originalLink.isEmpty()) {
            throw new NotFoundException("Original link id: " + id + " not found!");
        }
        originalLinkRepository.deleteById(id);
    }
}
