package com.bem.me.quer.domain.customer;

import com.bem.me.quer.domain.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerFactoryTest extends UnitTest {

    @Test
    @DisplayName("Should create a new Customer")
    void shouldCreateNewCustomer() {

        // given
        final var customer_name = "John Doe";
        final var customer_email = "johnDoe@email.com";
        final var customer_address = "Rua A";

        // when
        final var customer = CustomerFactory.create(
                customer_name,
                customer_email,
                customer_address
        );

        //then
        assertNotNull(customer);
        assertNotNull(customer.id());
        assertEquals(customer_name, customer.name());
    }
}