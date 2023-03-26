package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseURL= "http://3.238.220.2:8000";

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan resul

    @Test
    public void test1(){

  Response response= RestAssured.given().accept(ContentType.JSON).when().get(baseURL+"/api/spartans");
     //printing status code from object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole resullt body
        response.prettyPrint();

        //how to do API testing
        //verify status code 200
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type is application
        Assertions.assertEquals(response.contentType(),"application/json");

        //3:06





    }
}
