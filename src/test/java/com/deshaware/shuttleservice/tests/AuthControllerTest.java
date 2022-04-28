package com.deshaware.shuttleservice.tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.deshaware.shuttleservice.controller.AuthController;
import com.deshaware.shuttleservice.dto.RegisterRequest;
import com.deshaware.shuttleservice.model.Role;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    RegisterRequest newUserRegisterReq;
    
    @BeforeAll
    public void  initInput(){
        newUserRegisterReq = new RegisterRequest();
        newUserRegisterReq.setEmail("test@gmail.com");
        newUserRegisterReq.setName("Test");
        newUserRegisterReq.setPassword("12345");
        newUserRegisterReq.setRole(Role.USER);
    }


    @Test
    public void getAllUsers() throws Exception{
        this.mockMvc.perform(get("/api/auth/getAllUsers")).andDo(print()).andExpect(status().isAccepted())
        ;
    }

    @GetMapping(value="json", produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterRequest getUser(){
        return new RegisterRequest("email@gmail.com","name", "password",Role.USER);
    }

    @Test
    public void signUpTest() throws Exception {
        Object user = "{\"email\": \"ishan@gmail.com\", \"name\": \"Ishan Kishan\",\"role\": \"user\", \"password\": \"12345\"}";
        this.mockMvc.perform(
            post("/api/auth/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(user.toString()))
            .andExpect( status().isCreated() )
            .andExpect(jsonPath("$.message").value("User Created Successfully!"))
            ;

    }

    @Test
    public void deleteUser() throws Exception{
        String email = "ishan@gmail.com";
        this.mockMvc.perform(
            delete("/api/auth/delete/" + email))
            .andExpect(status().isNoContent());
            // .andExpect(jsonPath("$."))
        
    }

    @Test
    public void deleteUserFail() throws Exception{
        String email = "ishan@gmail.com";
        this.mockMvc.perform(
        delete("/api/auth/delete/" + email))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Failed to delete user"))
        .andExpect(jsonPath("$.data").value("User not found: " + email));
    }

}
