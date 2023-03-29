import { When, Then, And } from 'cypress-cucumber-preprocessor/steps';

Then('the collapsible content items are displayed', () => {
  cy.get('#page').find('details').its('length').should('be.gte', 2);
  cy.get('#page').find('details').should('be.visible');
});

And('no collapsible content items are expanded', () => {
  cy.get('#page').find('details').should('not.have.attr', 'open');
});

When('a collapsible content items is clicked on', () => {
  cy.get('#page').find('summary').first().click();
});

Then('the collapsible content items is expanded', () => {
  cy.get('#page').find('details').should('have.attr', 'open');
});

Then('and collapsible content is visible', () => {
  cy.get('#page')
    .contains('Who has reviewed and funded the project?')
    .should('be.visible');
});

When('the same collapsible content items is clicked on', () => {
  cy.get('#page').find('summary').first().click();
});

Then('the collapsible content items is not expanded', () => {
  cy.get('#page').find('details').should('not.have.attr', 'open');
});
