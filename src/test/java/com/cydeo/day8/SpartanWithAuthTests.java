package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanWithAuthTests extends SpartanAuthTestBase {
    @DisplayName("Get /api/spartans as a public user(guest)")
    @Test
    public void test1(){
        given().accept(ContentType.JSON).get("/api/spartans")
                .then().statusCode(401)
                .log().all();
    }

    @DisplayName("Get api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admin admin as a username and password

        given().auth().basic("admin","admin")
                .given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200).log().all();
    }
    @DisplayName("Delete api/spartans{id} as editor user expect 403")
    @Test
    public void testEditorDelete(){
        given().auth().basic("editor","editor")
                .given().accept(ContentType.JSON)
                .and().pathParam("id",100)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403).log().all();
    }
    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all
     */
}
