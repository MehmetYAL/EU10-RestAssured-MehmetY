package com.cydeo.day5;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){
        assertThat(5+5,is(10));
        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));

    }

    @DisplayName("Assertion with String")
    @Test
    public void test2(){

        String text="EU10 is learning Hamcrest";
        assertThat(text,is("EU10 is learning Hamcrest"));
        assertThat(text,equalTo("EU10 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU10 is learning Hamcrest")));

        //check if this text starts with EU10
        assertThat(text,startsWith("EU10"));
        //do assertions in case insensetive manner
        assertThat(text,startsWithIgnoringCase("eu10"));

        //check if text contains string

        assertThat(text,containsString("learning"));

        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="  ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }
    @DisplayName("Hamcrest for collection")
    @Test
    public void testCollection(){
        List<Integer> listOfNumbers=Arrays.asList(1,4,5,6,32,54,66,77,45,23);
        //check size of the list
        assertThat(listOfNumbers,hasSize(10));
        //check if lis has item any number(77)
        assertThat(listOfNumbers,hasItem(77));
        //check if this list hasItems--77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));

        //check if all numbers greater than0
        assertThat(listOfNumbers,everyItem(greaterThan(1)));
    }

}
