package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBAse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequest extends SpartanTestBAse {

    @DisplayName("get one spartan and convert it to spartan object")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 15).when()
                .get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();
        // De serialize----->json to pojo (java custom class)
        //2 different way
        //1.using as() method

        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to de serialize(converting JSON to Java class)

        Spartan spartan15 = response.as(Spartan.class);
        System.out.println("spartan15 = " + spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        //second way of deserialize to Java
        //2.using JsonPath to deseroalize to custom class

        JsonPath jsonPath=response.jsonPath();
        Spartan s15= jsonPath.getObject("",Spartan.class);
        System.out.println("s15 = " + s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());


    }

    @DisplayName("get one spartan from search endpoint")
            @Test
    public void test(){

        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO
        JsonPath jsonPath = given().accept(ContentType.JSON).and()
                .queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().extract().jsonPath();

        //get the first spartan from content and put inside spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200).extract().response();

        Search searchResult = response.as(Search.class);
        System.out.println(searchResult.getContent().get(0).getName());

    }

    @DisplayName("Get /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        List<Spartan> spartanList = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200).extract().jsonPath().getList("content", Spartan.class);


        System.out.println(spartanList.get(1).getName());


    }

}
