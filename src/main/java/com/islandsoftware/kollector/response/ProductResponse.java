package com.islandsoftware.kollector.response;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse(String name, String description, String specification, Set<String> categories,
                              int quantity, BigDecimal price, boolean active) {
}
