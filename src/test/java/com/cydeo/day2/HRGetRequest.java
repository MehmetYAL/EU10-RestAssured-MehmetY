package com.cydeo.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HRGetRequest {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://3.238.220.2:1000/ords/HR";
    }
      /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */
}
