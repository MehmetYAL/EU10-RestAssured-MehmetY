package com.cydeo.day4;

import com.cydeo.utilities.SpartanTestBAse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanWithJasonPath extends SpartanTestBAse {
    @DisplayName("Get one spartan with JasonPath")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //print name with path method
        System.out.println(response.path("name").toString());

        //assigning response to jsonpath
        JsonPath jsonPath=response.jsonPath();

        int id=jsonPath.getInt("id");
        String name= jsonPath.getString("name");
        long phone=jsonPath.getLong("phone");
        String gender=jsonPath.getString("gender");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("phone = " + phone);
        System.out.println("gender = " + gender);


    }
}
