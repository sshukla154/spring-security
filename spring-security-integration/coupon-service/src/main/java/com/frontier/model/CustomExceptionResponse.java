package com.frontier.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionResponse {

    Date exceptionDate;
    String messsage;
    String description;

}
