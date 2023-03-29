import { And } from 'cypress-cucumber-preprocessor/steps';

And('the Participant ticks the {string} checkboxes', (type) => {
  cy.get('#page')
    .find('.question-text')
    .contains(type)
    .parent()
    .find('input[type="checkbox"]')
    .click({ multiple: true });
});
