package com.frontier.controllers;

import com.frontier.dto.Coupon;
import com.frontier.exception.NoProductFoundException;
import com.frontier.model.Product;
import com.frontier.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @PostMapping(value = "/products")
    public Product create(@RequestBody Product product) {
        log.info("ProductRestController.create ::::::");
        if (StringUtils.hasText(product.getCouponCode())) {
            Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
            log.info("Found Coupon ::: {}", coupon.getDiscount());
            product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        }
        return repo.save(product);
    }

    @GetMapping(value = "/products/{productId}")
    public Product getProductById(@PathVariable Long productId) throws Exception {
        log.info("ProductRestController.getProductById ::::::");
        return repo.findById(productId).orElseThrow(() -> new NoProductFoundException("No product is found with ID " + productId));
    }

}
