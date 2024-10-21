package com.perinfinity.applicationservice.repository;

import com.perinfinity.applicationservice.entity.Candidatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidatures, Long> {
}
