package com.cydeo.day5;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class HamcrestMatchersApiTest {
    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */
        given().accept(ContentType.JSON).
                and().pathParam("id",15)
                .when()
                .get("http://3.238.220.2:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().assertThat()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),"name",is("Meta"),
                        "gender",is("Female"),"phone",is(1938695106));
    }
    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacgerData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("https://api.training.cydeo.com/teacher/{id}")
                .then().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers[0].firstName",is("Mario"))
                .body("teachers[0].lastName",is("Luigi"))
                .body("teachers[0].gender",equalTo("Male"));

    }
    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Erik,Porter,Ron
        given().accept(ContentType.JSON)
                .when().get("https://api.training.cydeo.com/teachers")
                .then().statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Erik","Porter","Ron"));
    }

}
