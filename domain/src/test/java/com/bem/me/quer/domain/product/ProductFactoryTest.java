package com.bem.me.quer.domain.product;

import com.bem.me.quer.domain.UnitTest;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.utils.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest extends UnitTest {

    @Test
    @DisplayName("should create a new product")
    void shouldCreateProduct(){
        // given
        final var productName = "T-shirt";
        final var productDescription = "some description";
        final var categoryId = CategoryId.from(Fixture.generateLongId());


        // when
        final var product = ProductFactory.create(
                productName,
                productDescription,
                categoryId
        );

        // then
        assertNotNull(product);
        assertNotNull(product.id());
        assertEquals(productName, product.name());
    }
}