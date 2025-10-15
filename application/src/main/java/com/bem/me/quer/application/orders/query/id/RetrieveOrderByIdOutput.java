package com.bem.me.quer.application.orders.query.id;

import java.time.LocalDateTime;
import java.util.Set;

public record RetrieveOrderByIdOutput(
        Long id,
        Long customerId,
        Set<Long> orderItems,
        String status,
        LocalDateTime createdAt

) {
}
