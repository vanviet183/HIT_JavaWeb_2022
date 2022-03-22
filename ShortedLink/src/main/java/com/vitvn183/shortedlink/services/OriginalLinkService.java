package com.vitvn183.shortedlink.services;

import com.vitvn183.shortedlink.entity.OriginalLink;
import com.vitvn183.shortedlink.model.dto.OriginalLinkDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OriginalLinkService {

    public List<OriginalLink> getAll();

    public Optional<OriginalLink> getFindById(Long id);

    public String getOriginalLink(String shortLink);

    public void addNew(OriginalLinkDto originalLinkDto);

    public void update(Long id, OriginalLinkDto originalLinkDto);

    public void delete(Long id);
}
