package com.bem.me.quer.application.product.commands.create;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.product.Product;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateProductUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should create a product with valid input")
    void shouldCreateProductWithValidInput(){

        //given
        final var repository = Mockito.mock(ProductRepository.class);

        final var useCase = new CreateProductUseCase(repository);

        final var input = new CreateProductInput(
                "T-shirt",
                "some description",
                Fixture.generateLongId()
        );

        Mockito.doReturn(ProductId.from(Fixture.generateLongId()))
                .when(repository)
                .persist(any(Product.class));

        //when

        final var result = useCase.execute(input);


        //then
        verify(repository, times(1)).persist(any(Product.class));
    }
}