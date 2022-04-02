package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.RegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest register) {
        
    }


    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public String login() {
        System.out.println("Called Login");
        return "Hello World!";
    }


}
