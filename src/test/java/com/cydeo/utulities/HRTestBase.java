package com.cydeo.utulities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {

    @BeforeAll
    public static void init(){
        baseURI=ConfigurationReader.getProperty("HrIP");



    }
}
