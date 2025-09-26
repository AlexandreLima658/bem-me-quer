package com.bem.me.quer.application.product.commands.delete;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteProductUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should delete a product")
    void shouldDeleteProduct(){
        //given
        final var repository = Mockito.mock(ProductRepository.class);
        final var productId = ProductId.from(Fixture.generateLongId());

        final var useCase = new DeleteProductUseCase(repository);

        //when
        useCase.execute(productId.value());

        //then
        Mockito.verify(repository, Mockito.times(1)).deleteById(productId);
    }
}