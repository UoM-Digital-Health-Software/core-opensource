import { Given, And } from 'cypress-cucumber-preprocessor/steps';

Given('the Participant has not accessed the questionnaire before', () => {
  cy.clearLocalStorage();
});

And(
  'the Participant opens the questionnaire with a Study and Participant Id',
  () => {
    cy.visitQuestionnaireWithStudyAndId();
  }
);
