package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.*;

public class ParametrizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5,21,32})
    public void testMultipleNumbers(int numbers){
        System.out.println("numbers = " + numbers);
        Assertions.assertTrue(numbers>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"jhon","mehmet","ali","ahmet"})
    public void testMultipleNames(String names){
        System.out.println("names = " + names);
    }
// SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints={22030,22031,22032,22033,22034,22035,22036})
    public void zipCodeTest(int zipCode){
        given().baseUri("https://api.zippopotam.us")
                .pathParam("zipCode",zipCode)
                .log().all()
                .when().get("/us/{zipCode}")
                .then().log().all().statusCode(200);

    }
}
