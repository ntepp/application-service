package com.perinfinity.applicationservice.entity;

import com.perinfinity.applicationservice.dto.CandidatureStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Candidatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String volunteeringId;
    private String opportunityId;
    private CandidatureStatus status;
    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate() {
        appliedAt = LocalDateTime.now();
    }
}