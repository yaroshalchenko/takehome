package com.challenge.demo.repository;

import com.challenge.demo.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteRepository extends JpaRepository<Site, Long> {

	Site findBySiteUUID(UUID uuid);
}
