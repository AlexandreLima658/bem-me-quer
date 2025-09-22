package com.bem.me.quer.application.product.query.filter;

import com.bem.me.quer.domain.pagination.Pagination;

public interface RetrieveProductsByFilterGateway {

    Pagination<RetrieveProductsByFilterOutput> execute(final RetrieveProductsByFilterInput input);
}
