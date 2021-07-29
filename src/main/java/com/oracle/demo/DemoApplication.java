package com.oracle.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.model.Customer;
import com.oracle.demo.service.CustomerService;
import com.oracle.demo.service.ReportService;
import com.oracle.demo.service.impl.CustomerServiceImpl;
import com.oracle.demo.service.impl.ReportServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        test();
    }

    public static void test() {
        CustomerService customerService = new CustomerServiceImpl();
        ReportService reportService = new ReportServiceImpl(customerService);

        List<Customer> customerList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int no = 0;
        System.out.println("Please Input Records: ");

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();
                customerList.addAll(customerService.readInput(line));
                ++no;
            } catch (Exception ex) {
                System.out.println("\nERROR WHILE READING INPUTS\n");
            }

            System.out.println("\n*************** Report for " + no + " lines *********************\n");
            try {
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(reportService.generateReport(customerList)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//            System.out.println(customerService.distinctCustomerIdForEachContractId(customerList));
//            System.out.println(customerService.distinctCustomerIdForEachGeoZone(customerList));
//            System.out.println(customerService.averageBuildDurationForEachGeoZone(customerList));
//            System.out.println(customerService.listOfCustomerIdForEachGeoZone(customerList));

        }

    }
}
