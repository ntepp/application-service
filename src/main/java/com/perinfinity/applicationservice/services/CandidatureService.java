package com.perinfinity.applicationservice.services;

import com.perinfinity.applicationservice.entity.Candidatures;

import java.util.Optional;

public interface CandidatureService {
    Candidatures apply(Candidatures candidate);
    Optional<Candidatures> getApplications(Long id);
}
