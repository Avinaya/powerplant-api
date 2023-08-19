package com.proshore.powerplant.exception;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    @NonNull
    private String errorMessage;

    @NotNull
    private String requestedURI;

    @NotNull
    private int statusCode;

    @NotNull
    private Instant timestamp;
}
