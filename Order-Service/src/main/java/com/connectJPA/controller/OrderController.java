package com.connectJPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{tableId}/create")
    public ResponseEntity<Order> createOrder(@PathVariable Long tableId, @RequestBody OrderRequest order) {
        Order newOrder = orderService.createOrder(tableId,order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderRequest request) {
        Order newOrder = orderService.updateOrder(
                request.getTableId(),
                request.getUserId(),
                request.getOrderDetails(),
                request.getNote()
        );
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable Long orderId) {
        List<OrderDetail> orderDetails = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id, @RequestParam("tableId") Long tableId){
        orderService.deleteOrder(id, tableId);
        return "Order deleted successfully!";
    }
}
