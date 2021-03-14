package com.challenge.demo.controller;

import com.challenge.demo.domain.Site;
import com.challenge.demo.repository.SiteRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SiteController.class)
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteRepository siteRepository;

    @Test
    public void testSiteController() throws Exception {
        mockMvc.perform(post("/sites")
                .content("{\"url\": \"www.bob.com\"}")
                .header("Content-Type", "application/json"))
                .andExpect(status().isCreated());

        Site site = new Site();
        site.setUrl("www.bob.com");
        then(siteRepository).should()
                .save(MockitoHamcrest
                        .argThat(org.hamcrest.Matchers
                                .hasProperty("url", Matchers.equalTo("www.bob.com"))));
    }
}
