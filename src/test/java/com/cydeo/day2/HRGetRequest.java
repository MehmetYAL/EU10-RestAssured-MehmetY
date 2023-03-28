package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRGetRequest {
    @BeforeAll
    public static void init(){
        baseURI="http://3.238.220.2:1000/ords/HR";


    }

    @Test
    public void test1(){
        Response response = RestAssured.get("/regions");
        //print the status code
        System.out.println(response.statusCode());

    }
      /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */
@DisplayName(("get request to /regions/2"))
    @Test
    public void test2(){

    Response response = RestAssured.get("/regions/2");
    //verify status code
assertEquals(200,response.statusCode());
    //verify content type
    assertEquals("application/json",response.contentType());
    //verify body contains Americas
    assertEquals("Americas",response.body().asString());

    response.prettyPrint();




}
}
