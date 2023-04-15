package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path Method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                when().queryParam("q","{\"region_id\":2}").and()
                .get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
//print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country Id
        System.out.println("response.path(\"items[0].country_id\") = " + response.path("items[0].country_id"));
        //print second country name
        String secondCountryName=response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print "http://52.207.61.129:1000/ords/hr/countries/CA"
        String CALink= response.path("items[2].links[0].href");
        System.out.println("CALink = " + CALink);

        // get me all country names
        List<String> countrynames=response.path("items.country_name");
        System.out.println("countrynames = " + countrynames);

        //assert that all regions ids are equal to 2

        List<Integer> allRegionsIDEqual2=response.path("items.region_id");
        for (Integer regionID : allRegionsIDEqual2) {
            assertEquals(2,regionID);
            System.out.println("regionID = " + regionID);
        }

    }
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //response.prettyPrint();

        //make sure we have only IT_Prog as a job_id

        List<String> allJobIds=response.path("items.job_id");
        for (String JobId : allJobIds) {

            assertEquals("IT_PROG",JobId);
            System.out.println("JobId = " + JobId);

        }
        //TASK:
        //print each name of IT_PROG
    }


}
