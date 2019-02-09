Feature: Compte

  Background:
    * url "http://192.168.99.100:8011"

  Scenario: Get All Comptes

    Given path '/compte/all'
    When method GET
    Then status 200

  Scenario: Get compte by id
    Given path '/compte/1'
    When method GET
    Then status 200

  Scenario: Remove compte
    Given path '/compte/delete/4'
    When method DELETE
    Then status 200
