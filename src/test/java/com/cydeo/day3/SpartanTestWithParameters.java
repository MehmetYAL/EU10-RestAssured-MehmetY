package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://3.238.220.2:8000";

        }
/*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */
    @DisplayName("Get request to api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id",5).when().
                get("/api/spartans/{id}");

        //response status code should be 200
        assertEquals(200,response.statusCode());

        //response content-type application/json
        assertEquals("application/json",response.contentType());

        //Blyte should be in ressponse payload
        assertTrue(response.body().asString().contains("Blythe"));


    }
    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */
    @DisplayName("GET request to /api/spartans/{id}, code should be 404/Negative test ")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).
                when().pathParam("id",500).and().
                get("/api/spartans/{id}");

        assertEquals(404,response.getStatusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));
    }
/*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search query parameter values are:gender|Female")
    @Test
    public void test3(){
        Response response=given().log().all().
                accept(ContentType.JSON).
                and().queryParam("gender","Female").
                and().queryParam("nameContains","e").
                get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }
    @DisplayName("GET request to /api/spartans/search query parameter values are:gender|Female")
    @Test
    public void test4(){

        //create a map and query parameters

        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response=given().log().all().
                accept(ContentType.JSON).
                and().queryParams(queryMap).
                when().get("/api/spartans/search");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

}
