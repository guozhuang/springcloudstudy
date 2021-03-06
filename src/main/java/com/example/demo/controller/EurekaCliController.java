package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.bean.UserUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;;
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
        //显然这里通过组件绑定了相应的接口，形成直接调用，
        //多种组件的情况下就需要考虑profile或者enable来配置自动装载
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

    @GetMapping("/eureka/instance")
    public List<ServiceInstance> getEurekaInstance(){
        String serviceId = "micr-service-user";
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        System.out.println("------------------------------------");
        if (list != null && list.size() > 0) {
            for (ServiceInstance instance : list) {
                System.out.println("服务Id : " + instance.getServiceId());
                System.out.println("host : " + instance.getHost());
                System.out.println("服务port : " + instance.getPort());
            }
        } else {
            System.out.println("注册中心无服务实例");
        }

        System.out.println("------------------------------------");
        return list;
    }

    //通过eureka调用具体逻辑
    @GetMapping("/eureka/user")
    public UserUri getUserInfo(){
        String serviceId = "micr-service-user";
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        System.out.println("------------------------------------");
        if (list != null && list.size() > 0) {
            int size = list.size() - 1;
            int index = (int)(0+Math.random()*(size-0+1));

            ServiceInstance instance = list.get(index);
            String url = "http://" + instance.getHost() + ":" + instance.getPort()
                    + "/1";

            System.out.println(url);
            UserUri userUri = this.restTemplate.getForObject(url, UserUri.class);
            userUri.setUri(url);

            return userUri;
        }

        return null;
    }

}
