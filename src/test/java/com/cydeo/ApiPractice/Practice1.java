package com.cydeo.ApiPractice;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;


import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Practice1 {
    @BeforeAll
    public static void SetUPClass(){
        baseURI="http://3.238.220.2:8000/";
    }
@Test
    public void Test(){

    Response response = given().accept(ContentType.JSON)
            .get("/api/spartans");
    assertEquals(response.statusCode(), 200);
    assertTrue(response.body().asString().contains("Allen"));


}
@Test
    public void Test2(){

    Response response = given().accept(ContentType.JSON)
            .get("/api/spartans");
    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json");


}
@Test
    public void Test3(){
  Response response =  given().accept(ContentType.JSON)
            .and().pathParam("id", 18)
            .when().get("/api/spartans/{id}");

  assertEquals(response.statusCode(),200);
 assertEquals(response.contentType(),"application/json");
 assertTrue(response.body().asString().contains("Allen"));

}
@Test
    public void test4(){

    Response response = given().accept(ContentType.JSON)
            .pathParam("id", 500)
            .when().get("/api/spartans/{id}");

    assertEquals(response.statusCode(),404);
    assertEquals(response.contentType(),"application/json");
    assertTrue(response.body().asString().contains("Spartan Not Found"));

}
@Test
    public void test5(){
    Response response = given().accept(ContentType.JSON)
            .queryParams("gender", "Female", "nameContains", "e")
            .when().get("/api/spartans/search");

    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json");
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));
}

@Test
    public void queryMap(){
    Map<String,Object> paramsmap=new HashMap<>();
    paramsmap.put("gender","Female");
    paramsmap.put("nameContains","J");
    Response response = given().accept(ContentType.JSON)
            .queryParams(paramsmap)
            .when().get("/api/spartans/search");

    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json");
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));
}
@Test
    public void testWithParamS(){

    Response response = given().accept(ContentType.JSON)
            .when().pathParam("id", 10)
            .when().get("/api/spartans/{id}");

    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json");

    System.out.println(response.body().path("id").toString());
    System.out.println(response.body().path("name").toString());
    System.out.println(response.body().path("gender").toString());
    System.out.println(response.body().path("phone").toString());

    int id=response.path("id");
    String name=response.path("name");
    String gender=response.path("gender");
    long phone=response.path("phone");
    System.out.println("id = " + id);
    System.out.println("name = " + name);
    System.out.println("gender = " + gender);
    System.out.println("phone = " + phone);

}
@Test
    public void test3(){
    Response response = when().get("/api/spartans");
    int firstId = response.path("id[0]");
    String secondName=response.path("name[1]");
    System.out.println(firstId);
    System.out.println(secondName);

    //extract all first names and print them

    List<String> firstNames=new ArrayList<>();
    //System.out.println(firstNames.add(response.path("name")));
    firstNames=response.path("name");
    System.out.println(firstNames);
    System.out.println(firstNames.size());

}

@Test
    public void testJsonPath(){

    Response response = given().accept(ContentType.JSON)
            .pathParam("id", "11")
            .when().get("/api/spartans/{id}");

    JsonPath json=response.jsonPath();
    int id=json.getInt("id");
    String name=json.getString("name");
    String gender=json.getString("gender");
    long phone=json.getLong("phone");
    //verify

    assertEquals(id,11);
    assertEquals(name,"Nona");

}
@Test
    public void test(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).assertThat().contentType("application/json")
                .and().assertThat().body("id",equalTo(15),"name",equalTo("Meta")
                ,"gender",equalTo("Female"),"phone",equalTo(1938695106));

    }

    @Test
    public void testDESerizilation(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        //convert Json response to Java Collections(Map)
        Map<String,Object> spartanMap=response.body().as(Map.class);

        System.out.println(spartanMap);
        //verify assertions
        assertEquals(spartanMap.get("name"),"Nona");
    }

//Authentication and authorization











}