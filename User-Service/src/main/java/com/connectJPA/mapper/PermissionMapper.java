package com.connectJPA.mapper;

import com.connectJPA.demo.dto.request.PermissionRequest;
import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.PermissionResponse;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.Permission;
import com.connectJPA.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponse toPermissionResponse(Permission permission);

    Permission toPermission(PermissionRequest request);
}



