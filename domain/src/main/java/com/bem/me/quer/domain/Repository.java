package com.bem.me.quer.domain;

import com.bem.me.quer.domain.commons.attributes.Identifier;
import com.bem.me.quer.domain.commons.entities.AggregateRoot;

import java.util.Optional;


public interface Repository<T extends AggregateRoot<?>, ID extends Identifier<?>> {

    Optional<T> findById(final ID id);

    ID persist(final T aggregate);

    void deleteById(final ID id);

    void deleteAll();

}
