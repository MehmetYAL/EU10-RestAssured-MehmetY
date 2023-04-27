package com.cydeo.ApiPractice;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;


import java.io.*;

public class JsonSchemaValidation {
    @BeforeAll
    public static void practice(){
        baseURI="http://3.238.220.2:8000";
    }

    @Test
    public void test1(){

given().accept(ContentType.JSON)
        .pathParam("id",118)
        .when().get("/api/spartans/{id}")
        .then().statusCode(200)
        .and().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PracticeValidationSchema.json"));




    }
}
