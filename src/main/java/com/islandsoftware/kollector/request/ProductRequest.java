package com.islandsoftware.kollector.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductRequest(@NotBlank String name, @NotBlank String description, String specification,
                             List<UUID> categories, @Min(1) int quantity, @DecimalMin("0.01") BigDecimal price,
                             boolean active) {
}
