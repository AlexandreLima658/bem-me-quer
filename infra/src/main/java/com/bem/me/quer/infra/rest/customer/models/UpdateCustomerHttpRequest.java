package com.bem.me.quer.infra.rest.customer.models;

import com.bem.me.quer.application.customer.commands.update.UpdateCustomerInput;

public record UpdateCustomerHttpRequest(
        String name,
        String email,
        String address
) {
    public UpdateCustomerInput toInput(final Long id) {
        return new UpdateCustomerInput(
                id,
                name,
                email,
                address
        );
    }
}
