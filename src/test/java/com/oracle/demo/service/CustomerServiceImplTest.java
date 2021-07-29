package com.oracle.demo.service;

import com.oracle.demo.model.Customer;
import com.oracle.demo.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(JUnit4.class)
public class CustomerServiceImplTest {

    private CustomerService customerService;

    private List<Customer> customerList;

    @Before
    public void init() {

        customerService = new CustomerServiceImpl();

        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n" +
                "3244332,2346,eu_west,YellowTeam5,ProjectTom,1999s";

        customerList = customerService.readInput(input);

    }

    @Test
    public void invalidInputTest() {

        String input = "2343225,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n";

        customerList = customerService.readInput(input);
        Assert.assertNull(customerList);

    }

    @Test
    public void distinctCustIdForEachContractIdTest() {

        Map<String, Integer> result = customerService.distinctCustomerIdForEachContractId(customerList);

        Assert.assertEquals(Integer.valueOf(3), result.get("2345"));
        Assert.assertEquals(Integer.valueOf(2), result.get("2346"));

    }

    @Test
    public void distinctCustIdForEachGeoZoneTest() {

        Map<String, Integer> result = customerService.distinctCustomerIdForEachGeoZone(customerList);

        Assert.assertEquals(Integer.valueOf(1), result.get("us_east"));
        Assert.assertEquals(Integer.valueOf(2), result.get("us_west"));
        Assert.assertEquals(Integer.valueOf(2), result.get("eu_west"));

    }

    @Test
    public void averageBuildDurationForEachGeoZoneTest() {

        Map<String, Double> result = customerService.averageBuildDurationForEachGeoZone(customerList);

        Assert.assertEquals(Double.valueOf(3445), result.get("us_east"));
        Assert.assertEquals(Double.valueOf(2216), result.get("us_west"));
        Assert.assertEquals(Double.valueOf(3481), result.get("eu_west"));

    }

    @Test
    public void listOfDistinctCustIdForEachGeoZoneTest() {

        Map<String, Set<String>> result = customerService.listOfCustomerIdForEachGeoZone(customerList);

        Assert.assertEquals(1, result.get("us_east").size());
        Assert.assertTrue(result.get("us_east").contains("2343225"));

        Assert.assertEquals(2, result.get("us_west").size());
        Assert.assertTrue(result.get("us_west").contains("1223456"));
        Assert.assertTrue(result.get("us_west").contains("1233456"));

        Assert.assertEquals(2, result.get("eu_west").size());
        Assert.assertTrue(result.get("eu_west").contains("3244332"));
        Assert.assertTrue(result.get("eu_west").contains("3244132"));

    }

}
