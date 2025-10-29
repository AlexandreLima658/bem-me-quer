package com.bem.me.quer.infra.jpa.order.item;

import com.bem.me.quer.infra.jpa.customer.CustomerJpaEntity;
import com.bem.me.quer.infra.jpa.order.OrderJpaEntity;
import com.bem.me.quer.infra.jpa.product.ProductJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Table(name = "orderItems")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class OrderItemJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private ProductJpaEntity product;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private OrderJpaEntity order;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "unitPrice")
  private BigDecimal unitPrice;

}
