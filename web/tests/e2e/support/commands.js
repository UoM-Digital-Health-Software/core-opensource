// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This is will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

Cypress.Commands.add('moveToNextQuestionPage', function () {
  cy.server();
  cy.route('POST', '/api/answers').as('newPage');
  cy.contains('Continue').click();
  cy.wait('@newPage');
  cy.get('@newPage').should('have.property', 'status', 201);
});

Cypress.Commands.add('visitQuestionnaireWithStudyAndId', function () {
  cy.visit({
    url: '/',
    qs: {
      study: Math.random().toString(36).slice(2),
      id: Math.random().toString(36).slice(2),
    },
  });
});

Cypress.Commands.add('completeConsentIfOnPage', function (currentPageName) {
  if (currentPageName === 'PAGE_QUESTIONNAIRE_CONSENT') {
    cy.get('input[type="checkbox"]').click({ multiple: true });
  }
});

Cypress.Commands.add('getLastNumberAnswer', () => {
  return cy.task('query', {
    sql: `
      SELECT answer.*
      FROM answer, question
      WHERE question.id = answer.question_id
      AND question.type="NUMBER_INPUT"
      AND answer.number is not null
      ORDER BY answer.id DESC
      LIMIT 1;
    `,
    values: [],
  });
});

Cypress.Commands.add('getLastTextAnswer', () => {
  return cy.task('query', {
    sql: `
      SELECT answer.*
      FROM answer, question
      WHERE question.id = answer.question_id
      AND question.type="TEXT_INPUT"
      AND answer.text is not null
      ORDER BY answer.id DESC
      LIMIT 1;
    `,
    values: [],
  });
});

Cypress.Commands.add('waitForFile', function (filename) {
  cy.readFile(filename, 'binary', { timeout: 15000 });
});
