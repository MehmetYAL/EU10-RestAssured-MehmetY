package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.List;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("Get request to /api/spartans")
    @Test
    public void test() {
        //we will ask for xml response
        //assert status code 200
        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade

        given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType("application/xml;charset=UTF-8")
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"))
                .log().all();


    }

    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath() {

        Response response = given()
                .accept(ContentType.XML) // we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        //get response xml body/payload and save inside the xmlpath object
        XmlPath xmlPath = response.xmlPath();
        System.out.println(xmlPath.getString("List.item[0].name"));
        System.out.println(xmlPath.getInt("List.item[2].id"));
        //how to get all names and save into list of string
        List<String> allNames = xmlPath.getList("List.item.name");
        //allNames.addAll(xmlPath.getList("List.item[].name"))
        System.out.println(allNames);


    }
}