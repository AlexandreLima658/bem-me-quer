package com.bem.me.quer.domain.order.item;

import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;
import com.bem.me.quer.domain.product.attributes.ProductId;

import java.math.BigDecimal;

public class OrderItem extends AggregateRoot<OrderItemId> {

    private final OrderId orderId;
    private final ProductId productId;
    private final Integer quantity;
    private final BigDecimal unitPrice;

    OrderItem(
            final OrderItemId orderItemId,
            final OrderId orderId,
            final ProductId productId,
            final Integer quantity,
            final BigDecimal unitPrice
    ) {
        super(orderItemId);
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderId orderId() {
        return orderId;
    }

    public ProductId productId() {
        return productId;
    }

    public Integer quantity() {
        return quantity;
    }

    public BigDecimal unitPrice() {
        return unitPrice;
    }

    public BigDecimal total(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
