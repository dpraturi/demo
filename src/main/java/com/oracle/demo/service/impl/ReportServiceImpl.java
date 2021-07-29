package com.oracle.demo.service.impl;

import com.oracle.demo.model.Customer;
import com.oracle.demo.model.Report;
import com.oracle.demo.service.CustomerService;
import com.oracle.demo.service.ReportService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * This class implements the ReportService interface to generate a report of provided customer list.
 */
@Component
public class ReportServiceImpl implements ReportService {

    private CustomerService customerService;

    public ReportServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Report generateReport(List<Customer> customerList) {

        if (!CollectionUtils.isEmpty(customerList)) {
            Report report = new Report();

            report.setNoOfDistinctCustIdForEachContractId(customerService.distinctCustomerIdForEachContractId(customerList));
            report.setNoOfDistinctCustIdForEachGeoZone(customerService.distinctCustomerIdForEachGeoZone(customerList));
            report.setAvgBuildDurationForEachGeoZone(customerService.averageBuildDurationForEachGeoZone(customerList));
            report.setListOfCustIdForEachGeoZone(customerService.listOfCustomerIdForEachGeoZone(customerList));

            return report;
        }
        return null;
    }

}
