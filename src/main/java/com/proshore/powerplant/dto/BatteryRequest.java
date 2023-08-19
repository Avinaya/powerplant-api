package com.proshore.powerplant.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatteryRequest {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Postcode is required")
    private String postcode;


    @NotNull(message = "Capacity is required")
    private Long capacity;
}
