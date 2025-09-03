package com.bem.me.quer.domain.category.attributes;

import com.bem.me.quer.domain.commons.attributes.Identifier;

public class CategoryId extends Identifier<Long> {

    public CategoryId(final Long value) {
        super(value);
    }

    public static CategoryId from(final Long value) {
        return new CategoryId(value);
    }

    public static CategoryId createNullValue() {
        return new CategoryId(null);
    }

}
