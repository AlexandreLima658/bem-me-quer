package com.bem.me.quer.application.orders.query.filter;

import com.bem.me.quer.domain.pagination.Pagination;

public interface RetrieveOrdersByFilterGateway {
    Pagination<RetrieveOrdersByFilterOutput> execute(final RetrieveOrdersByFilterInput input);
}
