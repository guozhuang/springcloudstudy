package com.example.demo.service.feign;
import com.example.demo.bean.UserUri;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo",url = "http://localhost:9000/eureka")
public interface OpenFeignService {

    //相当于子一级目录
    @Bean
    @GetMapping("user")
    UserUri test();
}
