package com.bem.me.quer.domain.category;

import com.bem.me.quer.domain.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryFactoryTest extends UnitTest {

    @Test
    @DisplayName("should create a new category")
    void shouldCreateCategory() {
        //given
        final var category_name = "Dresses";
        final var category_description = "A new dresses";
        final var category_createdAt = LocalDateTime.now();

        //when
        final var category = CategoryFactory.create(
                category_name,
                category_description,
                category_createdAt
        );

        //then
        assertNotNull(category);
        assertNotNull(category.id());
        assertEquals(category_name, category.name());
    }

}