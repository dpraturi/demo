package com.oracle.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private String customerId;

    private String contractId;

    private String geoZone;

    private String teamCode;

    private String projectCode;

    private Long buildDuration;

}
