package com.connectJPA.service;

import com.connectJPA.dto.request.TableRequest;
import com.connectJPA.dto.request.TableStatusRequest;
import com.connectJPA.entity.RestaurantTable;
import com.connectJPA.enums.TableStatus;
import com.connectJPA.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public RestaurantTable createTable(TableRequest tableRequest) {
        RestaurantTable table = new RestaurantTable();
        table.setCapacity(tableRequest.getCapacity());
        table.setTable_number(tableRequest.getTable_number());
        table.setStatus(tableRequest.getStatus()); // Mới tạo bàn sẽ là TRỐNG
        table.setNote(tableRequest.getNote());
        return tableRepository.save(table);
    }

    public RestaurantTable updateTableStatus(TableStatusRequest tableStatusRequest) {
        RestaurantTable table = tableRepository.findById(tableStatusRequest.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));
        table.setStatus(tableStatusRequest.getStatus()); // Cập nhật trạng thái bàn
        return tableRepository.save(table);
    }

    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    public Optional<RestaurantTable> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    public RestaurantTable updateTable(Long id, TableRequest updated) {
        return  tableRepository.findById(id).map(table -> {
            table.setTable_number(updated.getTable_number());
            table.setStatus(updated.getStatus());
            table.setCapacity(updated.getCapacity());
            table.setNote(updated.getNote());
            table.setReserved_at(updated.getReserved_at());
            return tableRepository.save(table);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy bàn với id: " + id));
    }

    public RestaurantTable updateStatusTable(Long id) {
        return  tableRepository.findById(id).map(table -> {
            table.setStatus(TableStatus.EMPTY);
            return tableRepository.save(table);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy bàn với id: " + id));
    }


    public void deleteTable(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bàn với id: " + id);
        }
        tableRepository.deleteById(id);
    }
}
