package com.frontier.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coupon {

    private Long id;

    private String code;

    private BigDecimal discount;

    private String expDate;

}
