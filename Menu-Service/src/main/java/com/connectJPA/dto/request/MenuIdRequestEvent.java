package com.connectJPA.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuIdRequestEvent {
    private Set<Object> menuIds;

}
