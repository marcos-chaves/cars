package com.ir.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarsController {
    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
    }
}
