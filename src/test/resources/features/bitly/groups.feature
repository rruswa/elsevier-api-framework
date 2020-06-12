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

  Scenario Outline: Retrieve Sorted Bitlinks for Group
    Given there are bitlinks available
      And a bitlink has been opened
    When a GET request is made to 'v4/groups/{group_guid}/bitlinks/clicks' when being sorted by <unit> for <units>
    Then the opened bitlink is returned

    Examples:
      | unit   | units |
      | minute | 3     |
      | hour   | 1     |
      | day    | 1     |
      | week   | 1     |
      | month  | 1     |