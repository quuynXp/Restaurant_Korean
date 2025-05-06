package com.connectJPA.controller;

import com.connectJPA.dto.request.UpdateStatusRequest;
import com.connectJPA.dto.response.OrderDetailResponse;
import com.connectJPA.entity.OrderDetail;
import com.connectJPA.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailService orderDetailsService;

    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailsService.createOrderDetail(orderDetail);
    }

    // GET danh sách theo status
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getByStatus(@RequestParam("status") OrderDetailStatus status) {
        return ResponseEntity.ok(orderDetailsService.getOrderDetailsByStatus(status));
    }

    // PUT cập nhật status
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDetail> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request
    ) {
        return ResponseEntity.ok(orderDetailsService.updateOrderDetailStatus(id, request.getStatus()));
    }

    // GET danh sách theo status trong ngày
    @GetMapping("/today")
    public ResponseEntity<List<OrderDetailResponse>> getAllTodayOrderDetailsByStatus(@RequestParam("status") OrderDetailStatus status){
        return ResponseEntity.ok(orderDetailsService.getAllTodayOrderDetailsByStatus(status));
    }


    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getByOrderId(@PathVariable Long orderId) {
        return orderDetailsService.getOrderDetailsByOrderId(orderId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailsService.getOrderDetailById(id));
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderDetail> create(@PathVariable Long orderId, @RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailsService.createOrderDetail(orderId, orderDetail), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        return ResponseEntity.ok(orderDetailsService.updateOrderDetail(id, orderDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
