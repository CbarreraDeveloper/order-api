package com.orderapi.order_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "ORDERS")
public class Order {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="REG_DATE", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> lines;

    @Column(name="TOTAL", nullable = false)
    private Double total;

}
