package com.proshore.powerplant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    @Column(updatable = false)
    private Instant created;
    private Instant modified;


    @PrePersist
    public void createdTimeStamps() {
        modified = Instant.now();
        created = Instant.now();
    }

    @PreUpdate
    public void updateTimeStamps() {
        modified = Instant.now();
    }
}
