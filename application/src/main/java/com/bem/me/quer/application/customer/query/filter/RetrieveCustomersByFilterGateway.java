package com.bem.me.quer.application.customer.query.filter;

import com.bem.me.quer.domain.pagination.Pagination;

public interface RetrieveCustomersByFilterGateway {

    Pagination<RetrieveCustomersByFilterOutput> execute(final RetrieveCustomersByFilterInput input);
}
