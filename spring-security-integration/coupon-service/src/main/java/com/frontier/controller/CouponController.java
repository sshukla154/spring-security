package com.frontier.controller;

//import com.frontier.exception.InvalidCouponException;
import com.frontier.exception.InvalidCouponException;
import com.frontier.model.Coupon;
import com.frontier.repository.CouponRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequestMapping("/couponapi")
@Transactional
public class CouponController {

    @Autowired
    CouponRepo couponRepo;

    @PostMapping("/coupons")
    public Coupon createCoupon(@RequestBody Coupon coupon){
        log.info("ProductRestController.createCoupon ::::::");
        return couponRepo.save(coupon);
    }

    @GetMapping("/coupons/{couponCode}")
    public Coupon getCoupon(@PathVariable String couponCode) {
        log.info("ProductRestController.getCoupon ::::::");
        Coupon byCode = couponRepo.findByCode(couponCode);
        if(byCode==null)
            throw new InvalidCouponException("Invalid coupons code : " + couponCode);
        return byCode;
    }

}
