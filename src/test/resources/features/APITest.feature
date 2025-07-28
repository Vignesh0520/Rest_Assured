Feature: Validate GET Users API using RestAssured

  Scenario: Validate response for GET list of users in page 2
    Given the base URI is "https://reqres.in"
    When I send GET request to "/api/users?page=2"
    Then the response status code should be 200
    And the response should contain "email"