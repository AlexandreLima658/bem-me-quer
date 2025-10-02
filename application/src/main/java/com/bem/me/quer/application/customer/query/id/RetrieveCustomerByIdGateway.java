package com.bem.me.quer.application.customer.query.id;

import com.bem.me.quer.application.category.query.id.RetrieveCategoryByIdOutput;

public interface RetrieveCustomerByIdGateway {
    RetrieveCustomerByIdOutput execute(final Long id);
}
