package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CBTrainingApiWithJasonPath {

    String baseURL= "https://api.training.cydeo.com";



    @DisplayName("GET reguest to individual student")
    @Test
    public void test(){
        //send a get request to student id 47 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = chunked
        //verify Date header exists
        //assert that
            /*
                firstName Alexis
                batch 9
                section N/A
                emailAddress lbrickwoodd@angelfire.com
                companyName Zoomdog
                state California
                zipCode 23803
                using JsonPath
             */

        Response response=given().accept(ContentType.JSON).
                and().pathParam("id",47).
                when().get(baseURL+"/student/{id}");

        JsonPath jsonPath=response.jsonPath();


        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8");
  Assertions.assertEquals("chunked",response.header("transfer-encoding"));



    }

}
