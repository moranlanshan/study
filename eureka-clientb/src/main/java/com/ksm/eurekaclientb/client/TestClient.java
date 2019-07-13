package com.ksm.eurekaclientb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "EUREKA-CLIENT")
public interface TestClient {

    @GetMapping("/msg")
    public String get();
}
