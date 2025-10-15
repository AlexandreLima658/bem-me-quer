package com.bem.me.quer.application.orders.query.filter;

import java.time.LocalDateTime;
import java.util.Set;

public record RetrieveOrdersByFilterOutput(
        Long id,
        Long customerId,
        Set<Long> orderItems,
        String status,
        LocalDateTime createdAt

) {
}
