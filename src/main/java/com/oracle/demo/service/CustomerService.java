package com.oracle.demo.service;

import com.oracle.demo.model.Customer;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This Interface contains utility methods to perform some operations on customer data taken from input as String.
 */
public interface CustomerService {

    List<Customer> readInput(String input);

    Map<String, Integer> distinctCustomerIdForEachContractId(List<Customer> customerList);

    Map<String, Integer> distinctCustomerIdForEachGeoZone(List<Customer> customerList);

    Map<String, Double> averageBuildDurationForEachGeoZone(List<Customer> customerList);

    Map<String, Set<String>> listOfCustomerIdForEachGeoZone(List<Customer> customerList);

}
