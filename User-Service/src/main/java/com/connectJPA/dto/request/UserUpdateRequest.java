package com.connectJPA.dto.request;

import com.connectJPA.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping
public class UserUpdateRequest {
    String username;
    String password;
    String email;
    String phone;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;
    List<Role> roles;
}
