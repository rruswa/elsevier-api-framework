Feature: Groups

  Scenario: GET group by group GUID
    Given there are group resources
    When a GET request is made to 'v4/groups/{group_guid}'
    Then the response status code is 200

  Scenario: User is Forbidden when they GET group by GUID that they don't belong to
    Given the user is trying to access a group they aren't a part of
    When a GET request is made to 'v4/groups/{group_guid}'
    Then the response status code is 403
    And the error message contains the following properties
      |  message  | resource | description                                          |
      | FORBIDDEN | groups   | You are currently forbidden to access this resource. |