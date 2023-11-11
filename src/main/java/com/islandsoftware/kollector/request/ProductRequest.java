package com.islandsoftware.kollector.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(String name, String description, String specification,
                             UUID category, int quantity, BigDecimal price) {
}
