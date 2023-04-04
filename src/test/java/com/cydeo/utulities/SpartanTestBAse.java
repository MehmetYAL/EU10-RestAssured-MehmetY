package com.cydeo.utulities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBAse {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("SpartanIP");

        //String dbUrl="jdbc:oracle:thin:@3.238.220.2:1521:xe";
       String dbUrl="jdbc:oracle:thin:@3.238.220.2:1521:XE";
        String dbUserName="SP";
        String dbPassword="SP";
        DBUtils.createConnection(dbUrl,dbUserName,dbPassword);


    }

    @AfterAll
    public void tearDown(){
        DBUtils.destroy();
    }
}
