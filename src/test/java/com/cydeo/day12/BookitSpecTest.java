package com.cydeo.day12;

import com.cydeo.utilities.BookitTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import com.cydeo.utilities.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class BookitSpecTest extends BookitTestBase {
    @Test
    public void test1() {
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
given().spec(teacherReqSpec)
        .when().get("/api/users/me")
        .then().spec(getDynamicResSpec(200));

    }

    @Test
    public void test2() {
        //send a get request to /api/users/me endpoint as a student-member
        //verify status code and content type

        given().spec(studentMemberReqSpec)
                .when().get("/api/users/me")
                .then().spec(getDynamicResSpec(200))
                .spec(userCheck("Marius","Forker"));

    }
    //1.50
}
