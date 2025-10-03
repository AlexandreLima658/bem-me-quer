package com.bem.me.quer.application.customer.commands.update;

import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.customer.CustomerFactory;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UpdateCustomerUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should update customer ")
    void shouldUpdateCustomer() {

        // given
        final var repository = Mockito.mock(CustomerRepository.class);
        final var customerId = CustomerId.from(Fixture.generateLongId());

        final var customer = CustomerFactory.create(
                customerId,
                "Alfredo",
                "al@email.com",
                "rua F"
        );

        final var newName = "Alfredo Rex";
        final var useCase = new UpdateCustomerUseCase(repository);

        final var input = new UpdateCustomerInput(
                customer.id().value(),
                newName,
                "al@emai.com",
                "rua F"
        );

        Mockito.when(
                repository.findById(any(CustomerId.class))
        ).thenReturn(Optional.of(customer));

        // when
        final var result = useCase.execute(input);

        // then
        assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1)).persist(customer);
    }
}