package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * 调用eureka服务，并且再进行服务调用
 */
@RestController
public class EurekaCliController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/eureka/client")
    public List<String> getEurekaServices(){
        List<String> list = discoveryClient.getServices();
        System.out.println("------------------------------------");
        if (list != null && list.size() > 0) {
            for (String serviceId : list) {
                System.out.println("服务Id : " + serviceId);
            }
        } else {
            System.out.println("注册中心无服务实例");
        }

        System.out.println("------------------------------------");
        return list;
    }
}
