package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;

import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;


import java.io.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",13)
                .and().
                auth().basic("admin","admin")
                .and().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }
    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

given().accept(ContentType.JSON)
        .auth().basic("admin","admin")
        .when().get("/api/spartans")
        .then().statusCode(200)
        //if your json file is not under resources we should give/copy it with path
        .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));
    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema
//2.53
}
