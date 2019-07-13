package com.ksm.eurekaclientb.controller;

import com.ksm.eurekaclientb.config.GirlConfig;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrilController {
    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/girl/print")
    public String print(){
        return "name: " + girlConfig.getName() +", age: " + girlConfig.getAge();
    }
}
