package com.bem.me.quer.infra.jpa.order;

import com.bem.me.quer.domain.order.Order;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaEntity;
import com.bem.me.quer.infra.jpa.order.item.OrderItemJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class OrderJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private CustomerJpaEntity customerId;

  @OneToMany(mappedBy = "order", cascade = ALL, fetch = LAZY, orphanRemoval = true)
  private Set<OrderItemJpaEntity> orderItems;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Order.OrderStatus status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
