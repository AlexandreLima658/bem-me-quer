package com.bem.me.quer.application.category.query.filter;


import com.bem.me.quer.domain.pagination.Pagination;

public interface RetrieveCategoriesByFilterGateway {

    Pagination<RetrieveCategoriesByFilterOutput> execute(final RetrieveCategoriesByFilterInput input);
}
