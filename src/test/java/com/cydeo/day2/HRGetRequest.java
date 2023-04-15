package com.cydeo.day2;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRGetRequest extends HRTestBase {


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
