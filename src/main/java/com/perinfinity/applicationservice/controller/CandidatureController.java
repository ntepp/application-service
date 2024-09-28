package com.perinfinity.applicationservice.controller;

import com.perinfinity.applicationservice.entity.Candidatures;
import com.perinfinity.applicationservice.services.CandidatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/applications")
@Controller
public class CandidatureController {
    CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Candidatures> getCandidatures(@PathVariable Long id) {
        var candidature = candidatureService.getApplications(id);
        if(candidature.isPresent()) {
            return ResponseEntity.ok(candidature.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<String> apply(@RequestBody Candidatures candidature) {
        candidatureService.apply(candidature);
        return ResponseEntity.ok("SUCCESS");
    }
}
