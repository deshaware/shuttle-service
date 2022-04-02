package com.deshaware.shuttleservice.dto;


import com.deshaware.shuttleservice.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 public class RegisterRequest {
    private String email;
    private String name;
    private String password;
    private Role role;

}
