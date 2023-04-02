package com.cydeo.day4;

import com.cydeo.utulities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJasonPath extends HRTestBase {
    @DisplayName("GET reguest to Countries")
    @Test
    public void test1(){
     Response response= get("/countries");
     //get the second country name with JsonPath

        JsonPath jsonPath=response.jsonPath();
        String countryName2=jsonPath.getString("items[1].country_name");
        System.out.println("countryName2 = " + countryName2);


        //get all contries ID
        List<String> allCountryIds=jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2
        List<String> allCountryRegionIs2=jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("allCountryRegionIs2 = " + allCountryRegionIs2);

    }

@DisplayName("GET request /employees with query param")
    @Test
    public void test2(){
   Response response= given().queryParam("limit",107).
    when().get("/employees");

        //get me all e-mail of employees who is working as IT_Prog
    JsonPath jsonPath=response.jsonPath();

    List<String> employeeITProg = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
    System.out.println("employeeITProg = " + employeeITProg);

    //get me first name of employees who is making more than 10000
    List<String> salaryMoreThan10000=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
    System.out.println("salaryMoreThan10000 = " + salaryMoreThan10000);

    //get the max salary firstname
    String maxSalrFirstName=jsonPath.getString("items.max {it.salary}.first_name");
    System.out.println("maxSalrFirstName = " + maxSalrFirstName);


}



}
