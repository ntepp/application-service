package com.perinfinity.applicationservice.controller;

import com.perinfinity.applicationservice.entity.Candidatures;
import com.perinfinity.applicationservice.services.CandidatureService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/applications")
public class CandidatureController {
    CandidatureService candidatureService;
    private final Counter applicationCounter;
    public CandidatureController(CandidatureService candidatureService, MeterRegistry registry) {
        this.candidatureService = candidatureService;
        applicationCounter = Counter.builder("application.counter")
                .description("Count the number of applications")
                .tags("action", "post-application")
                .register(registry);
    }


    @GetMapping("/{id}")
    public Candidatures getCandidatures(@PathVariable Long id) {
        return candidatureService.getApplications(id).orElse(null);

    }

    @PostMapping
    public ResponseEntity<Void> apply(@RequestBody Candidatures candidature) {
        applicationCounter.increment();
        Candidatures candidatures = candidatureService.apply(candidature);
        return entityWithLocation(candidatures.getId());
    }

    private ResponseEntity<Void> entityWithLocation(Long resourceId) {
        //Create the URI location of the new candidate
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
