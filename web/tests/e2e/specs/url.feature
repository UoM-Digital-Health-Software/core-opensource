Feature: URL

  Scenario: Opening the Questionnaire with No Parameters
    Given the Participant has not accessed the questionnaire before
    When they visit the questionnaire without any URL Parameters
    Then they are asked to use a valid URL issued by a study team
    And the questionnaire is not displayed

  Scenario: Opening the Questionnaire with only Study Id
    Given the Participant has not accessed the questionnaire before
    When they visit the questionnaire with only a Study Id
    Then they are asked to use a valid URL issued by a study team
    And the questionnaire is not displayed

  Scenario: Opening the Questionnaire with only Participant Id
    Given the Participant has not accessed the questionnaire before
    When they visit the questionnaire with only a Participant Id
    Then they are asked to use a valid URL issued by a study team
    And the questionnaire is not displayed

  Scenario: Opening the Questionnaire with Study Id & Participant Id
    Given the Participant has not accessed the questionnaire before
    When the Participant opens the questionnaire with a Study and Participant Id
    Then the questionnaire is displayed
    And they are not asked to use a valid URL issued by a study team


  Scenario: Opening the Questionnaire without Parameters (after previous access)
    Given the Participant has not accessed the questionnaire before
    And the Participant opens the questionnaire with a Study and Participant Id
    And the questionnaire is displayed
    When they visit the questionnaire without any URL Parameters
    Then the page reloads
    And the questionnaire is displayed
    And they are not asked to use a valid URL issued by a study team

  Scenario: Opening the Questionnaire with different Parameters
    Given the Participant has not accessed the questionnaire before
    And the Participant opens the questionnaire with a Study and Participant Id
    And the questionnaire is displayed
    When they visit the questionnaire with different Study and Participant Ids
    Then the page reloads
    And the questionnaire is displayed
    And they are not asked to use a valid URL issued by a study team
