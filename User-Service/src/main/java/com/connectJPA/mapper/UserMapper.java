package com.connectJPA.mapper;

import com.connectJPA.dto.request.UserCreationRequest;
import com.connectJPA.dto.request.UserUpdateRequest;
import com.connectJPA.dto.response.UserResponse;
import com.connectJPA.entity.User;
import com.connectJPA.entity.elasticsearch.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(UserCreationRequest request);


    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserDocument toUserDocument(User user);
}



