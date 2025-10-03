package com.bem.me.quer.application.customer.commands.create;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class CreateCustomerUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("Should create a customer with valid input")
    void shouldCreateCustomerWithValidInput(){

        // given
        final var repository = Mockito.mock(CustomerRepository.class);

        final var useCase = new CreateCustomerUseCase(repository);

        final var input = new CreateCustomerInput(
                "Julia",
                "ju@email.com",
                "rua a"
        );

        Mockito.doReturn(CustomerId.from(Fixture.generateLongId()))
                .when(repository)
                .persist(any(Customer.class));

        // when
        final var result = useCase.execute(input);

        // then
        Mockito.verify(repository, Mockito.times(1)).persist(any(Customer.class));
    }
}