package com.bem.me.quer.infra.jpa.product;


import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Table(name = "products")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpaEntity category;


}
