package com.proshore.powerplant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCodeRangeResponse {

    @NotNull
    private List<String> names;

    @NotNull
    private double averageCapacity;

    @NotNull
    private long totalCapacity;

}
