package com.challenge.demo.repository;

import com.challenge.demo.domain.Question;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

//	@Query(value = "SELECT q.* FROM questions q WHERE q.site_id = ?1", nativeQuery = true)
	List<Question> findAllBySiteSiteId(Long siteId);


//	@Query(value = "select q.* FROM questions q JOIN site s on q.site_id = s.site_id where s.site_uuid = ?1",
//			nativeQuery = true)
	List<Question> findAllBySiteSiteUUID(UUID uuid);


//	@Query(value = "select q FROM Question q JOIN FETCH q.parent p JOIN FETCH p.parent pp")
//	List<Question> findAllFetch();
}
