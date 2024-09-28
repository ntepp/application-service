package com.perinfinity.applicationservice.services;

import com.perinfinity.applicationservice.entity.Candidatures;
import com.perinfinity.applicationservice.repository.CandidatureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatureServiceImpl implements CandidatureService {
    CandidatureRepository candidatureRepository;

    public CandidatureServiceImpl(CandidatureRepository candidatureRepository) {
        this.candidatureRepository = candidatureRepository;
    }

    /**
     *
     * @param candidate
     * @return Candidatures
     * TODO: Verify that the volunteering_i exist
     * TODO: Verify that opportunity_id exists
     * DONE: Register the candidature in DB
     */
    @Override
    public Candidatures apply(Candidatures candidate) {
        try {

            Candidatures candidatures = candidatureRepository.save(candidate);
            return candidatures;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     *
     * @param id
     * @return Candidatures
     */
    @Override
    public Optional<Candidatures> getApplications(Long id) {
       return candidatureRepository.findById(id);
    }
}
