package com.islandsoftware.kollector.response;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record ProductResponse(UUID uuid, String name, String description, String specification, Set<String> categories,
                              int quantity, BigDecimal price, boolean active) {
}
