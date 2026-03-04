Feature: API Automation

  @api
  Scenario: Validate response of GET request

    Given Have GET request specs for execution
    When User hit GET request
    Then Validate GET API response

  @api
  Scenario: Validate response of POST request

    Given Have POST request for execution
    When User hit POST request with body
    Then Validate POST API response

  @api
  Scenario: Validate response of typicode GET request

    Given Have typicode GET request specs for execution
    When User hit typicode GET request
    Then Validate typicode GET API response