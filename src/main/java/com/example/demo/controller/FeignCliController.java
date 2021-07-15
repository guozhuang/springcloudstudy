package com.example.demo.controller;

import com.example.demo.bean.UserUri;
import com.example.demo.service.feign.OpenFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignCliController {
    @Autowired
    OpenFeignService openFeignService;

    /**
     * 面对的是interface时，需要一个实现类，如果有多个实现，可以进行指定
     * 或在某个实现类上指定@Primary
     * 或者在调用方@Autowired后使用@Qualifier
     */

    /**
     * @Autowired
     * https://blog.csdn.net/z828849eser/article/details/87565458
     * 1.Spring先去容器中寻找NewsSevice类型的bean（先不扫描newsService字段）
     *
     * 2.若找不到一个bean，会抛出异常
     *
     * 3.若找到一个NewsSevice类型的bean，自动匹配，并把bean装配到newsService中
     *
     * 4.若NewsService类型的bean有多个，则扫描后面newsService字段进行名字匹配，匹配成功后将bean装配到newsService中
     */

    @GetMapping("/feign/test")
    public UserUri test(){
        return openFeignService.test();
    }
}
