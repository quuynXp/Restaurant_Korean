package com.connectJPA.controller;

import com.connectJPA.dto.request.TableStatusRequest;
import com.connectJPA.entity.RestaurantTable;
import com.connectJPA.service.OrderService;
import com.connectJPA.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@RequestBody TableRequest tableRequest) {
        RestaurantTable restaurantTable = tableService.createTable(tableRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTable);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        List<RestaurantTable> restaurantTable = tableService.getAllTables();
        return ResponseEntity.ok(restaurantTable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTable> getTableById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable Long id, @RequestBody TableRequest table) {
        RestaurantTable newTable = tableService.updateTable(id, table);
        return ResponseEntity.ok(newTable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{tableId}/status")
    public ResponseEntity<RestaurantTable> updateTableStatusAfterOrder(
            @PathVariable Long tableId) {
        RestaurantTable restaurantTable = orderService.updateTableStatusAfterOrder(tableId);
        return ResponseEntity.ok(restaurantTable);
    }

    @PutMapping("/status")
    public ResponseEntity<RestaurantTable> updateTableStatus(
            @RequestBody TableStatusRequest tableStatusRequest) {
        RestaurantTable restaurantTable = tableService.updateTableStatus(tableStatusRequest);
        return ResponseEntity.ok(restaurantTable);
    }

}
