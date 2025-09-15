package com.bem.me.quer.application.category.commands.delete;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.category.CategoryRepository;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class DeleteCategoryUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should delete a category")
    void shouldDeleteCategory(){

        //given
        final var repository = Mockito.mock(CategoryRepository.class);
        final var categoryId = CategoryId.from(new Random().nextLong());

        final var useCase = new DeleteCategoryUseCase(repository);

        //when
        useCase.execute(categoryId.value());

        //then
        Mockito.verify(repository, Mockito.times(1)).deleteById(categoryId);
    }
}