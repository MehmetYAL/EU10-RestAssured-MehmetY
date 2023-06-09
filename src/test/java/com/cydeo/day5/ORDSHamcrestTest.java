package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName(("Get request to Employess IT_PROG endpoint and chaining"))
    @Test
    public void test(){

        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names

        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employess")
                .then().statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ"))
                .body("items.firts_name",equalTo(names));//equality of lists assertion
    }

    //1.54

    @DisplayName( " ")
    @Test
    public void employeesTest2(){

        JsonPath jsonPath=  given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        //extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }
}
