package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    //我们就构建一个简易的数据存储,Product需要在feign-client-intf中定义
    public static final Map<Long, Product> items = new ConcurrentHashMap<>();

    @GetMapping("/detail")
    public Product getProduct(Long pid) {
        if(!items.containsKey(pid)) {
            Product product = Product.builder()
                                .productId(pid)
                                .description("good，another one")
                                .stock(100L)
                                .build();
            items.putIfAbsent(pid, product);
        }
        return items.get(pid);
    }

    @GetMapping("/buy")
    public String buy(Long pid) {
        Product product = items.get(pid);
        if(null == product) {
            return "Product Not Found";
        }
        if(product.getStock() <= 0L) {
            return "Sold Out";
        }
        //
        synchronized (product){
           if(product.getStock() <= 0L) {
               return "Sold Out";
           }
           product.setStock(product.getStock() - 1);
        }
        return "Order Placed";
    }
}
