package com.proshore.powerplant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryResponse {

    @NotNull
    @JsonProperty("id")
    private String entityId;

    @NotNull
    private String name;

    @NotNull
    private String postcode;

    @NotNull
    private Long capacity;


}
