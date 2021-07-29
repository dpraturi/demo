package com.oracle.demo.service;

import com.oracle.demo.model.Customer;
import com.oracle.demo.model.Report;
import com.oracle.demo.service.impl.CustomerServiceImpl;
import com.oracle.demo.service.impl.ReportServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class ReportServiceImplTest {

    private ReportService reportService;

    private CustomerService customerService;

    private List<Customer> customerList;

    @Before
    public void init() {

        customerService = new CustomerServiceImpl();
        reportService = new ReportServiceImpl(customerService);


        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n" +
                "3244332,2346,eu_west,YellowTeam5,ProjectTom,1999s";

        customerList = customerService.readInput(input);

    }

    @Test
    public void generateReportTest() {

        Report report = reportService.generateReport(customerList);

        Assert.assertEquals(Integer.valueOf(3), report.getNoOfDistinctCustIdForEachContractId().get("2345"));
        Assert.assertEquals(Integer.valueOf(2), report.getNoOfDistinctCustIdForEachContractId().get("2346"));

        Assert.assertEquals(Integer.valueOf(1), report.getNoOfDistinctCustIdForEachGeoZone().get("us_east"));
        Assert.assertEquals(Integer.valueOf(2), report.getNoOfDistinctCustIdForEachGeoZone().get("us_west"));
        Assert.assertEquals(Integer.valueOf(2), report.getNoOfDistinctCustIdForEachGeoZone().get("eu_west"));

        Assert.assertEquals(Double.valueOf(3445), report.getAvgBuildDurationForEachGeoZone().get("us_east"));
        Assert.assertEquals(Double.valueOf(2216), report.getAvgBuildDurationForEachGeoZone().get("us_west"));
        Assert.assertEquals(Double.valueOf(3481), report.getAvgBuildDurationForEachGeoZone().get("eu_west"));

        Assert.assertEquals(1, report.getListOfCustIdForEachGeoZone().get("us_east").size());
        Assert.assertTrue(report.getListOfCustIdForEachGeoZone().get("us_east").contains("2343225"));

        Assert.assertEquals(2, report.getListOfCustIdForEachGeoZone().get("us_west").size());
        Assert.assertTrue(report.getListOfCustIdForEachGeoZone().get("us_west").contains("1223456"));
        Assert.assertTrue(report.getListOfCustIdForEachGeoZone().get("us_west").contains("1233456"));

        Assert.assertEquals(2, report.getListOfCustIdForEachGeoZone().get("eu_west").size());
        Assert.assertTrue(report.getListOfCustIdForEachGeoZone().get("eu_west").contains("3244332"));
        Assert.assertTrue(report.getListOfCustIdForEachGeoZone().get("eu_west").contains("3244132"));
    }

}
