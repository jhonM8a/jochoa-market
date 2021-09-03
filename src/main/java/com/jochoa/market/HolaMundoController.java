package com.jochoa.market;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class HolaMundoController {
    @GetMapping("/hello")
    public String syHello(){
        return "Hello men!!";
    }
}
