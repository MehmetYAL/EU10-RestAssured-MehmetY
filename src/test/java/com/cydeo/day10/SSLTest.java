package com.cydeo.day10;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
public class SSLTest {
    @Test
    public void test1(){
        given().relaxedHTTPSValidation().//even if it doesnt valid certificate i want
        when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }
    @Test
    public void keyStore(){

        given().keyStore("pathofile","password")
                .when().get("apiyrl");
    }
}
