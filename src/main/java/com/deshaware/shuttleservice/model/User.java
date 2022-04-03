package com.deshaware.shuttleservice.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @NotBlank(message = "email cannot be blank")
   private String email;

    @NotBlank(message = "Name cannot be blank")
   private String name;

   @Enumerated(EnumType.STRING)
   private Role role;
    
   private String password; 

   private Instant modified;
   private boolean enabled;
}
