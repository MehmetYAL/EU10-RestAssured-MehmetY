package com.cydeo.day6;
import com.cydeo.pojo.Links;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utulities.HRTestBase;
import com.cydeo.utulities.SpartanTestBAse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
