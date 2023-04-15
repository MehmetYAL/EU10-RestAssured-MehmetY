package com.cydeo.day6;
import com.cydeo.pojo.*;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @DisplayName("get request to region")
    @Test
    public void test1(){

       JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().get("/regions")
                .then().statusCode(200).log().body()
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

//02.30

    }

    @DisplayName("Get request to/employees and only 4values of pojo class we want to ignore the others ")
    @Test
    public void employeeGet(){

        Employee employee1 = given().contentType(ContentType.JSON).get("/employees")
                .then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println("employee1 = " + employee1);

    }
    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields
     */

    @DisplayName("Get request regions id s are 1,2,3,4, ignore non used fields")
    @Test
    public void testGetRegionIDs1234(){
        //send e get request and save everything inside the regions object
        //since we prepare pojo also for all properties we dont need to use any path so as() method is enough
        Regions regions = get("/regions").then()
                .statusCode(200)
                .extract().response().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(),is(4));

        //create empty list to store values

        List<String> regionNames=new ArrayList<>();
        List<Integer> regionsID=new ArrayList<>();
        //get list of regions out of regions object
        List<Region> items=regions.getItems();
//loop through each of region , save their ids and names to empty list that we prepared
        for (Region region : items) {

            regionNames.add(region.getRegion_name());
            regionsID.add(region.getRegionId());
                      //03.10
        }
        System.out.println("regionsID = " + regionsID);
        System.out.println("regionNames = " + regionNames);
        List<String> expectedRegionNames= Arrays.asList("Europe","Americas", "Asia", "Middle East and Africa");
        List<Integer> expectedRegionID=Arrays.asList(1,2,3,4);

        //compare two result
        assertThat(regionsID,is(expectedRegionID));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));


    }

}
