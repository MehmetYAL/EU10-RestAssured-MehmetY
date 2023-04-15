package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBAse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanHamcrestTest extends SpartanTestBAse {

    @DisplayName("Get spartan/search an chaining together")
    @Test
    public void test1(){

        List<String> names = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().jsonPath()
                .getList("content.name");
        System.out.println("names = " + names);

    }

    @Test
    public void test2(){

        //save status code

        int statusCode = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().statusCode()                ;
        System.out.println(statusCode);

    }
}
