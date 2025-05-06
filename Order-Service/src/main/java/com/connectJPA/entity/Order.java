package com.connectJPA.entity;

import com.connectJPA.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private Long userId;
    private Double totalPrice;
    private String note;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "table_id")
    @JsonManagedReference
    private RestaurantTable table; // Mỗi order sẽ liên kết với một bàn

    @OneToMany(mappedBy = "order",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails; // Chi tiết món ăn trong order
}