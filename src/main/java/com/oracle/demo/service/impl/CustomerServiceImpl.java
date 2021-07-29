package com.oracle.demo.service.impl;

import com.oracle.demo.model.Customer;
import com.oracle.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is an implementation of CustomerService interface which of contains utility
 * methods to perform some operations on customer data taken from input as String.
 */
@Component
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * This method reads String input and returns customer list.
     */
    @Override
    public List<Customer> readInput(String input) {

        if (input != null && !input.isBlank()) {
            List<Customer> customerList = new ArrayList<>();

            try {
                String[] records = input.split("\n");

                for (String record : records) {
                    String[] columns = record.split(",");
                    customerList.add(new Customer(columns[0], columns[1], columns[2], columns[3], columns[4],
                            Long.parseLong(columns[5].substring(0, columns[5].length() - 1))));
                }
            } catch (Exception ex) {
                logger.error("Invalid input provided");
                return null;
            }

            return customerList;
        }

        return null;
    }

    /**
     * This method reads customer list and returns unique customerId for each contractId.
     */
    @Override
    public Map<String, Integer> distinctCustomerIdForEachContractId(List<Customer> customerList) {
        if (CollectionUtils.isEmpty(customerList)) {
            return null;
        }
        return customerList.stream().collect(Collectors.groupingBy(Customer::getContractId,
                Collectors.collectingAndThen(Collectors.groupingBy(Customer::getCustomerId, Collectors.counting()), Map::size)));
    }

    /**
     * This method reads customer list and returns unique customerId for each geoZone.
     */
    @Override
    public Map<String, Integer> distinctCustomerIdForEachGeoZone(List<Customer> customerList) {
        if (CollectionUtils.isEmpty(customerList)) {
            return null;
        }
        return customerList.stream().collect(Collectors.groupingBy(Customer::getGeoZone,
                Collectors.collectingAndThen(Collectors.groupingBy(Customer::getCustomerId, Collectors.counting()), Map::size)));
    }

    /**
     * This method reads customer list and returns average buildDuration for each geoZone.
     */
    @Override
    public Map<String, Double> averageBuildDurationForEachGeoZone(List<Customer> customerList) {
        if (CollectionUtils.isEmpty(customerList)) {
            return null;
        }
        return customerList.stream().collect(Collectors.groupingBy(Customer::getGeoZone,
                Collectors.averagingDouble(Customer::getBuildDuration)));
    }

    /**
     * This method reads customer list and returns list of unique customerId for each geoZone.
     */
    @Override
    public Map<String, Set<String>> listOfCustomerIdForEachGeoZone(List<Customer> customerList) {
        if (CollectionUtils.isEmpty(customerList)) {
            return null;
        }
        return customerList.stream().collect(Collectors.groupingBy(Customer::getGeoZone,
                Collectors.mapping(Customer::getCustomerId, Collectors.toSet())));
    }

}
