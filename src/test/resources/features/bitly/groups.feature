Feature: Groups

  Scenario: GET group by group GUID
    Given there are group resources
    When a GET request is made to 'v4/groups/{group_guid}'
    Then the response status code is 200