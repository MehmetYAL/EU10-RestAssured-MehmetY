package com.cydeo.day5;

import com.cydeo.utulities.DBUtils;
import com.cydeo.utulities.SpartanTestBAse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBAse {

    @DisplayName("get one spartan from api and database")
    @Test
    public void testDB1(){
//get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone  from database

        String query="select spartan_id,name,gender,phone from spartans\\n\" +\n" +
                "                \"where spartan_id = 15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);


    }

}
