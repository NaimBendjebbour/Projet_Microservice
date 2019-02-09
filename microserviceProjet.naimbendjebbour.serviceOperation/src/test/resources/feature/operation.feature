Feature: Operation

  Background:
    * url "http://192.168.99.100:8010"

  Scenario: Get All Operation

    Given path '/operation/all'
    When method GET
    Then status 200

  Scenario: Get operation by id
    Given path '/operation/1'
    When method GET
    Then status 200

  Scenario: Remove operation
    Given path '/operation/delete/4'
    When method DELETE
    Then status 200
