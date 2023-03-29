import { When, Then, And } from 'cypress-cucumber-preprocessor/steps';

// Scenario: Opening the Questionnaire with No Parameters
When('they visit the questionnaire without any URL Parameters', () => {
  cy.visit('/');
});

Then('they are asked to use a valid URL issued by a study team', () => {
  cy.contains('Please use the link provided by your study team').should(
    'be.visible'
  );
});

And('the questionnaire is not displayed', () => {
  cy.contains('The relationship between physical and mental health').should(
    'not.exist'
  );
});

// Scenario: Opening the Questionnaire with only Study Id
When('they visit the questionnaire with only a Study Id', () => {
  cy.visit({
    url: '/',
    qs: {
      study: 'hryws',
    },
  });
});

// Scenario: Opening the Questionnaire with only Participant Id
When('they visit the questionnaire with only a Participant Id', () => {
  cy.visit({
    url: '/',
    qs: {
      id: 'abc987654321',
    },
  });
});

// Scenario: Opening the Questionnaire with Study Id & Participant Id
Then('the questionnaire is displayed', () => {
  cy.contains('You are being invited to take part in a research study').should(
    'be.visible'
  );
});

Then('they are not asked to use a valid URL issued by a study team', () => {
  cy.contains('Please use a URL from your study team.').should('not.exist');
});

// Scenario: Opening the Questionnaire without Parameters (after previous access)
Then('the page reloads', () => {
  cy.contains('Loading...').should('be.visible');
  cy.contains('Loading...').should('not.exist');
});

// Scenario: Opening the Questionnaire with different Parameters
When(
  'they visit the questionnaire with different Study and Participant Ids',
  () => {
    cy.visit({
      url: '/',
      qs: {
        study: 'actissist',
        id: 'VBQKRGLT',
      },
    });
  }
);
