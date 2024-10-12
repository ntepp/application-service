package com.perinfinity.applicationservice.controller;

import com.perinfinity.applicationservice.entity.Candidatures;
import com.perinfinity.applicationservice.services.CandidatureService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
@Controller
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
    public ResponseEntity<Candidatures> getCandidatures(@PathVariable Long id) {
        var candidature = candidatureService.getApplications(id);
        return candidature.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<String> apply(@RequestBody Candidatures candidature) {
        applicationCounter.increment();
        candidatureService.apply(candidature);
        return ResponseEntity.ok("SUCCESS");
    }
}
