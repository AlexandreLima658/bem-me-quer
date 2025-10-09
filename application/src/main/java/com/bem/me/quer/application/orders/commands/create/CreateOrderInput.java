package com.bem.me.quer.application.orders.commands.create;

import java.time.LocalDateTime;

public record CreateOrderInput(
        LocalDateTime date
) {
}
