package com.proshore.powerplant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "battery")
public class Battery extends AbstractEntity {

    @NotNull
    @Column(name = "entity_id", nullable = false, unique = true, updatable = false, length = 50)
    private String entityId;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 255, message = "Name must be between 1 and 255 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Postcode is required")
    @Size(min = 1, max = 50, message = "Postcode must be between 1 and 50 characters")
    @Column(nullable = false)
    private String postcode;


    @NotNull(message = "Capacity is required")
    @Column(nullable = false)
    private Long capacity;

}
