package com.connectJPA.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyRevenueDTO {
    private Integer dayOfWeek;
    private String weekday;
    private Double totalRevenue;
}
