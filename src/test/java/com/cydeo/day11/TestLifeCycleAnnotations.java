package com.cydeo.day11;

import org.junit.jupiter.api.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.*;


import java.io.*;

public class TestLifeCycleAnnotations {


        //beforeClass is testNg version of beforeAll, same logic
        @BeforeAll
        public static void init(){
            System.out.println("Before all is running");
        }
        //beforeMethod is testNg version of beforeEach, same logic
        @BeforeEach
        public void initEach(){
            System.out.println("\tBefore each is running");
        }

        @AfterEach
        public void closeEach(){
            System.out.println("\tAfter each is running");
        }


    @Test
    public void test1(){}
        @Disabled
        @Test
        public void test2(){
            System.out.println("Test 2 is running");
        }

        @AfterAll
        public static void close(){
            System.out.println("After all is running");
        }
    }

