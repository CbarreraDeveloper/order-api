package com.orderapi.order_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ORDER_LINE")
public class OrderLine {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="FK_ORDER", nullable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT", nullable = false)
    private Product product;
    @Column(name = "PRICE", nullable = false)
    private Double price;
    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;
    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine orderLine)) return false;
        return Objects.equals(id, orderLine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
