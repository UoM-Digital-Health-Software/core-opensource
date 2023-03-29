import { Then, And } from 'cypress-cucumber-preprocessor/steps';

// Scenario: Viewing the Physical and Mental Health content
Then('the first page is the introduction', () => {
  cy.contains('Introduction')
    .should('be.visible')
    .should('have.prop', 'tagName')
    .should('equal', 'H1');
});
And('the introduction content is visible', () => {
  cy.contains('You are being invited to take part in a research study').should(
    'be.visible'
  );
});
And('the video placeholder visible', () => {
  cy.get('.video').should('be.visible');
});
