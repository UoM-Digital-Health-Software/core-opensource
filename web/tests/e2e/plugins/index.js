/* eslint-disable arrow-body-style */
// https://docs.cypress.io/guides/guides/plugins-guide.html

// if you need a custom webpack configuration you can uncomment the following import
// and then use the `file:preprocessor` event
// as explained in the cypress docs
// https://docs.cypress.io/api/plugins/preprocessors-api.html#Examples

// /* eslint-disable import/no-extraneous-dependencies, global-require */
// const webpack = require('@cypress/webpack-preprocessor')

const cucumber = require('cypress-cucumber-preprocessor').default;
const mysql = require('mysql2');
const fs = require('fs');
const { readPdf } = require('./read-pdf');

const database = mysql.createConnection({
  host: 'localhost',
  user: 'core',
  password: 'core123',
  database: 'core',
});

module.exports = (on, config) => {
  on('file:preprocessor', cucumber());

  on('task', {
    async query({ sql, values }) {
      return (await database.promise().query(sql, values))[0];
    },
    deleteFile(filename) {
      if (fs.existsSync(filename)) {
        fs.unlinkSync(filename);
      }
      return true;
    },
    readPdf,
  });

  return Object.assign({}, config, {
    fixturesFolder: 'tests/e2e/fixtures',
    integrationFolder: 'tests/e2e/specs',
    screenshotsFolder: 'tests/e2e/screenshots',
    videosFolder: 'tests/e2e/videos',
    supportFile: 'tests/e2e/support/index.js',
  });
};
