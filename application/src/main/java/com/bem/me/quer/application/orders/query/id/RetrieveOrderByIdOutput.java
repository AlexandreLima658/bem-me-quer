package com.bem.me.quer.application.orders.query.id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record RetrieveOrderByIdOutput(
        Long id,
        Long customerId,
        Set<Long> orderItems,
        BigDecimal totalAmount,
        String status,
        LocalDateTime createdAt

) {
}
