package com.bem.me.quer.application.customer.commands.delete;


import com.bem.me.quer.application.IntegrationTest;
import com.bem.me.quer.domain.commons.utils.Fixture;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteCustomerUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("should delete a customer")
    void shouldDeleteCustomer(){
        // given
        final var repository = Mockito.mock(CustomerRepository.class);
        final var customerId = CustomerId.from(Fixture.generateLongId());

        final var useCase = new DeleteCustomerUseCase(repository);

        // when
        useCase.execute(customerId.value());

        // then
        Mockito.verify(repository, Mockito.times(1)).deleteById(customerId);
    }
}