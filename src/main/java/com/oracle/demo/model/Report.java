package com.oracle.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class Report {

    private Map<String, Integer> noOfDistinctCustIdForEachContractId;

    private Map<String, Integer> noOfDistinctCustIdForEachGeoZone;

    private Map<String, Double> avgBuildDurationForEachGeoZone;

    private Map<String, Set<String>> listOfCustIdForEachGeoZone;

}
