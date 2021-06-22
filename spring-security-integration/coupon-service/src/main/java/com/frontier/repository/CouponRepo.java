package com.frontier.repository;

import com.frontier.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    Coupon findByCode(String couponCode);

}
