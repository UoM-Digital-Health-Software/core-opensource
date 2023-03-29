import { Then, And, When } from 'cypress-cucumber-preprocessor/steps';

const PAGE_DATA_CSV = `
1;true;PAGE_STATIC_INTRODUCTION;1;false;;;Introduction;
12;true;PAGE_QUESTIONNAIRE_CONSENT;3;false;;;Consent;
`;

const PAGE_ACTIVE_INDEX = 1;
const PAGE_NAME_INDEX = 2;
const PAGE_ORDER_INDEX = 3;
const PAGE_TITLE_INDEX = 7;

const pages = PAGE_DATA_CSV.split('\n')
  .filter((line) => line != '')
  .map((line) => line.split(';'))
  .sort((row1, row2) => row1[PAGE_ORDER_INDEX] - row2[PAGE_ORDER_INDEX])
  .map((row) => {
    return {
      active: row[PAGE_ACTIVE_INDEX],
      name: row[PAGE_NAME_INDEX],
      title: row[PAGE_TITLE_INDEX],
    };
  })
  .filter((row) => row.active);

When(
  'the Participant navigates to the {string} page called {string}',
  (ignore, pageName) => {
    let pageIndex = 0;
    while (pages[pageIndex].name != pageName) {
      const currentPageName = pages[pageIndex].name;
      cy.completeConsentIfOnPage(currentPageName);
      cy.moveToNextQuestionPage();
      pageIndex++;

      const pageTitle = pages[pageIndex].title;
      cy.contains(pageTitle).should('be.visible');
    }
  }
);

Then(
  'the Partipant is on the {string} page called {string}',
  (ignore, pageName) => {
    const page = pages.find((page) => page.name === pageName);
    cy.get('.intro').get('h1').contains(page.title).should('be.visible');
  }
);

And('the next page is {string}', (title) => {
  cy.moveToNextQuestionPage();
  cy.contains(title).should('be.visible');
});

And('the Participant completes the questionnaire', () => {
  for (const index in pages) {
    const currentPageName = pages[index].name;
    cy.completeConsentIfOnPage(currentPageName);
    cy.moveToNextQuestionPage();
  }
  cy.contains('you have completed the questionnaire').should('be.visible');
});

Then('the Partipant is still on completed page', () => {
  cy.contains('you have completed the questionnaire').should('be.visible');
});

And('the Participant clicks Continue', () => {
  cy.moveToNextQuestionPage();
});

And('there is no continue button', () => {
  cy.contains('Continue').should('not.exist');
});

Then(
  'they visit every page of the questionnaire, the exit button is visible',
  () => {
    for (const index in pages) {
      cy.contains('Exit').should('be.visible');
      const currentPageName = pages[index].name;
      cy.completeConsentIfOnPage(currentPageName);
      cy.moveToNextQuestionPage();
    }
  }
);

And('the Participant clicks the browser back button', () => {
  cy.wait(2000);
  cy.go('back');
});
