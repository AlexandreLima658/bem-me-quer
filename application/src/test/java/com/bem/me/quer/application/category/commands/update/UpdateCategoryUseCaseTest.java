package com.bem.me.quer.application.category.commands.update;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.category.CategoryFactory;
import com.bem.me.quer.domain.category.CategoryRepository;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.utils.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateCategoryUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should update a category")
    void shouldUpdateCategory() {

        // given
        final var repository = mock(CategoryRepository.class);


        final var category = CategoryFactory.create(
                CategoryId.from(Fixture.generateLongId()),
                "Dresses",
                "some description",
                LocalDateTime.now()
        );


        final var newName = "T-shirts";
        final var useCase = new UpdateCategoryUseCase(repository);

        final var input = new UpdateCategoryInput(
                category.id().value(),
                newName,
                "some description",
                LocalDateTime.now()
        );

        when(
                repository.findById(any(CategoryId.class))
        ).thenReturn(Optional.of(category));


        // when
        final var result = useCase.execute(input);

        // then
        assertNotNull(result);
        verify(repository, times(1)).persist(category);
    }
}