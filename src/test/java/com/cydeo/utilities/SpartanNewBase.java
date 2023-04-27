package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.Request;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responceSpec;
    public static RequestSpecification userSpec;
    @BeforeAll
    public static void init(){
        //save baseUrl inside this vatiable so that we dont need to type each http method
        baseURI="http://3.238.220.2";
        port=7000;
        basePath="/api";

        requestSpec = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .log().all();

        responceSpec = expect().statusCode(200)
                .contentType(ContentType.JSON).logDetail(LogDetail.ALL);

        userSpec=given().accept(ContentType.JSON)
                .and().auth().basic("user", "user")
                .log().all();
    }
    @AfterAll
    public static void close(){
        //reset the info we set above, method comes from restassured
        reset();
    }
}
