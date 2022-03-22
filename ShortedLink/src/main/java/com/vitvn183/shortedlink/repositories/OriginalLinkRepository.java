package com.vitvn183.shortedlink.repositories;

import com.vitvn183.shortedlink.entity.OriginalLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalLinkRepository extends JpaRepository<OriginalLink, Long> {

    OriginalLink findOriginalLinkByShortLink(String shortLink);
}
