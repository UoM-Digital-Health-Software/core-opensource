import { Then, When, And } from 'cypress-cucumber-preprocessor/steps';

Then('there is a link to the Seeking Help page', () => {
  cy.get('#heading').contains('Seeking help').should('be.visible');
});

When('the Participant clicks on the link to the Seeking Help page', () => {
  cy.get('#heading').contains('Seeking help').click();
});

Then('the Seeking Help page is displayed', () => {
  cy.get('h1')
    .contains('Seeking mental health assistance')
    .should('be.visible');
  cy.url().should('include', '/help');
});

And('there is a section for {string}', (heading) => {
  cy.get('h3').contains(heading).should('be.visible');
});

Then('the Seeking Help page is not displayed', () => {
  cy.url().should('not.include', '/help');
});
