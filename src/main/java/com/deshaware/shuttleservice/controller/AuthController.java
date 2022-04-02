package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.RegisterRequest;
import org.springframework.web.bind.annotation.*;
import com.deshaware.shuttleservice.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest register) { // transfered from DTO
        User user = new User();
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());
        


        
    }


    // @RequestMapping(value="/", method= RequestMethod.GET)
    // @ResponseBody
    // public String login() {
    //     System.out.println("Called Login");
    //     return "Hello World!";
    // }


}
