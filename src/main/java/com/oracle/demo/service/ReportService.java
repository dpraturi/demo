package com.oracle.demo.service;

import com.oracle.demo.model.Customer;
import com.oracle.demo.model.Report;

import java.util.List;

/**
 * This interface provided a method to generate a report of provided customer list.
 */
public interface ReportService {

    Report generateReport(List<Customer> customerList);

}
