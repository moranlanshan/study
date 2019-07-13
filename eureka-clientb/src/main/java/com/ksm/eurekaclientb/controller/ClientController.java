package com.ksm.eurekaclientb.controller;

import com.ksm.eurekaclientb.client.TestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {


 @Autowired
 private TestClient testClient;

 @GetMapping("/getmsg")
 public String getMsg(){
  return testClient.get();
 }



   @Autowired
    private LoadBalancerClient loadBalancerClient;

  /*  @Autowired
    private RestTemplate restTemplate;*/

    @GetMapping("/get")
    public String get(){
        //1.直接使用RestTemplate,写死URL
       /* RestTemplate template = new RestTemplate();
        String response = template.getForObject("http://localhost:8087/msg", String.class);
*/
       RestTemplate template = new RestTemplate();
        ServiceInstance choose = loadBalancerClient.choose("EUREKA-CLIENT");
        String host = choose.getHost();
        int port = choose.getPort();

        String url = String.format("http://%s:%s",host,port)+"/msg";
        String response = template.getForObject(url, String.class);
        //3.可以使用 @LoadBalanced ，直接使用应用名称
        //String response = restTemplate.getForObject("http://EUREKA-CLIENT/msg", String.class);
        log.info(response);
        return  response;

    }
}
