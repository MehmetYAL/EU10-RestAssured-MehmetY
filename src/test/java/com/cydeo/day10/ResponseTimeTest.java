package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {
    @Test
    public void test1(){

        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when().get("/api/spartans")
                        .then()
                                //.time(lessThanOrEqualTo(2000l)) we can use one value
                .time(both(greaterThan(500l)).and(lessThanOrEqualTo(2000l)))// we can use if we will give range for testing time
                                        .extract().response();
        System.out.println("response.getTime() = " + response.getTime());

    }

}
