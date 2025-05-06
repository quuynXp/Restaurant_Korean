package com.connectJPA.mapper;

import com.connectJPA.demo.dto.request.PermissionRequest;
import com.connectJPA.demo.dto.request.RoleRequest;
import com.connectJPA.demo.dto.response.PermissionResponse;
import com.connectJPA.demo.dto.response.RoleResponse;
import com.connectJPA.demo.entity.Permission;
import com.connectJPA.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
}



