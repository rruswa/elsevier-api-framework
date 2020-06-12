Feature: Bitlinks

  Scenario: Create a Bitlink that already exists
    Given a valid bitlink payload has been initialised
    When a POST request is made to 'v4/bitlinks' with payload
    Then the response status code is 200
    And the bitlink has been created

  Scenario: Create a Bitlink resource that doesn't already exist
    Given a valid dynamic bitlink payload has been initialised
    When a POST request is made to 'v4/bitlinks' with payload
    Then the response status code is 201
    And the bitlink has been created