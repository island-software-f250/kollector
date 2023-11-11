package com.islandsoftware.kollector.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductUpdateRequest(String name, String description, String specification,
                                   List<UUID> categories, int quantity, BigDecimal price, boolean active) {
}
