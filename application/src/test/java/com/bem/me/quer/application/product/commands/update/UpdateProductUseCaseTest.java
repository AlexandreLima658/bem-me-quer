package com.bem.me.quer.application.product.commands.update;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.product.ProductFactory;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateProductUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should update a product")
    void shouldUpdateProduct(){
        // given
        final var repository = mock(ProductRepository.class);


        final var product = ProductFactory.create(
                ProductId.from(Fixture.generateLongId()),
                "Dresses",
                "some description",
                CategoryId.from(Fixture.generateLongId())
        );


        final var newName = "T-shirts";
        final var useCase = new UpdateProductUseCase(repository);

        final var input = new UpdateProductInput(
                product.id().value(),
                newName,
                "some description",
                Fixture.generateLongId()
        );

        when(
                repository.findById(any(ProductId.class))
        ).thenReturn(Optional.of(product));


        // when
        final var result = useCase.execute(input);

        // then
        assertNotNull(result);
        verify(repository, times(1)).persist(product);
    }
}