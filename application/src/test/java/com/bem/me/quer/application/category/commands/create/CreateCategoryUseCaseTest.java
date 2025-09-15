package com.bem.me.quer.application.category.commands.create;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.category.Category;
import com.bem.me.quer.domain.category.CategoryRepository;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.utils.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCategoryUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should create a category with valid input")
    void shouldCreateACategoryWithValidInput(){

        // given
        final var repository = Mockito.mock(CategoryRepository.class);


        final var useCase = new CreateCategoryUseCase(repository);


        final var input = new CreateCategoryInput(
                "Dresses",
                "A new dresses",
                LocalDateTime.now()
        );

        Mockito.doReturn(CategoryId.from(Fixture.generateLongId()))
                .when(repository)
                .persist(any(Category.class));



        //when
        final var result = useCase.execute(input);

        //then
        verify(repository, times(1)).persist(any(Category.class));


    }
}