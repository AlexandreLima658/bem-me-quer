package com.bem.me.quer.domain.commons.entities;

import com.bem.me.quer.domain.commons.attributes.BaseEntity;
import com.bem.me.quer.domain.commons.attributes.Identifier;

public class AggregateRoot<Id extends Identifier<?>> extends BaseEntity<Id> {

    protected AggregateRoot(Id id) {
        super(id);
    }
}
