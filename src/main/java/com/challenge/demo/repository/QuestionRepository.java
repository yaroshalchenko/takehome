package com.challenge.demo.repository;

import com.challenge.demo.domain.Question;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findAllBySiteSiteId(Long siteId);
  List<Question> findAllBySiteSiteUUID(UUID uuid);
}
