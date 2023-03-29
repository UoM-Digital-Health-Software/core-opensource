Feature: Collapsible Content

    Scenario: Viewing a page with collapsible content
        Given the Participant opens the questionnaire with a Study and Participant Id
        Then the collapsible content items are displayed
        And no collapsible content items are expanded

    Scenario: Expanding collapsible content
        Given the Participant opens the questionnaire with a Study and Participant Id
        And the collapsible content items are displayed
        When a collapsible content items is clicked on
        Then the collapsible content items is expanded
        Then and collapsible content is visible

    Scenario: Collapsing collapsible content
        Given the Participant opens the questionnaire with a Study and Participant Id
        And the collapsible content items are displayed
        And a collapsible content items is clicked on
        And the collapsible content items is expanded
        When the same collapsible content items is clicked on
        Then the collapsible content items is not expanded
