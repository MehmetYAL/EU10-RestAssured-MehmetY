package com.cydeo.day7;

import com.cydeo.utulities.SpartanTestBAse;
import org.junit.jupiter.api.Test;

public class SpartanPostRequestDemo extends SpartanTestBAse {
      /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void test(){}
}
