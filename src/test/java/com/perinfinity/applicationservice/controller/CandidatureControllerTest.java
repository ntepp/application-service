package com.perinfinity.applicationservice.controller;

import com.perinfinity.applicationservice.dto.CandidatureStatus;
import com.perinfinity.applicationservice.entity.Candidatures;
import com.perinfinity.applicationservice.services.CandidatureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CandidatureControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    void getCandidatures() {
        Candidatures candidatures = new Candidatures();
        candidatures.setAppliedAt(LocalDateTime.now());
        candidatures.setStatus(CandidatureStatus.PENDING);
        candidatures.setOpportunityId("aaa123");
        candidatures.setVolunteeringId("bbb123");
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/api/applications",candidatures,String.class );

        Candidatures application = testRestTemplate.getForObject("/api/applications/1", Candidatures.class);
        Assertions.assertNotNull(application);
        Assertions.assertEquals(candidatures.getOpportunityId(), application.getOpportunityId());
        Assertions.assertEquals(candidatures.getVolunteeringId(), application.getVolunteeringId());
    }

    @Test
    void apply() {
        Candidatures candidatures = new Candidatures();
        candidatures.setAppliedAt(LocalDateTime.now());
        candidatures.setStatus(CandidatureStatus.PENDING);
        candidatures.setOpportunityId("aaa123");
        candidatures.setVolunteeringId("bbb123");
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/api/applications",candidatures,String.class );
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals("/api/applications/2", responseEntity.getHeaders().getLocation().getPath());
    }
}