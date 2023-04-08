package com.cydeo.day7;

import com.cydeo.utulities.SpartanTestBAse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utulities.SpartanTestBAse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import com.cydeo.utulities.SpartanTestBAse;
import org.junit.jupiter.api.Test;

public class PutAndPatchRequestDemo extends SpartanTestBAse {

    @DisplayName("Put request to one spartan for update with Map")
    @Test
    public void PUTRequest(){

        //just like post request we have different options to send body, we will go with map

        Map<String,Object> putrequestMap=new LinkedHashMap<>();
        putrequestMap.put("name","BruceWayne");
        putrequestMap.put("gender","Male");
        putrequestMap.put("phone",8877111222L);

        given().contentType(ContentType.JSON).body(putrequestMap)
                .and().pathParam("id",112)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

    }
    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);
        putRequestMap.put("name","Peter");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",100)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send


    }
    @DisplayName("Delete request to one spartan ")
    @Test
    public void deleteSpartan(){

        given().pathParam("id",114)
                .when().delete("api/spartans/{id}");

        //send a get request after you delete make sure you are getting 404
    }


}
