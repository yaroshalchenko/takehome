package com.challenge.demo.repository;

import com.challenge.demo.domain.Site;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class SiteRepositoryTest {

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void findById(){
        Site expected = new Site()
                .setSiteId(1L)
                .setSiteUUID(UUID.randomUUID());
        siteRepository.save(expected);

        assertEquals(expected, siteRepository.findById(1L).get());
    }
}
