Feature: Content Pages

    Scenario: Viewing the Physical and Mental Health content
        Given the Participant opens the questionnaire with a Study and Participant Id
        Then the first page is the introduction
        And the introduction content is visible
        And the video placeholder visible
