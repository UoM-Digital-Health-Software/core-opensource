import { Then, And } from 'cypress-cucumber-preprocessor/steps';

Then('the displayed page is {string}', (title) => {
  cy.contains(title).should('be.visible');
});

And('the questionnaire completed page is displayed', () => {
  cy.contains('Thank you - you have completed the questionnaires.').should(
    'be.visible'
  );
});
