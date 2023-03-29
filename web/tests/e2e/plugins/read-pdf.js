const fs = require('fs');
const parse = require('pdf-parse');

const readPdf = (filename) => {
  const pdf = fs.readFileSync(filename);
  return parse(pdf).then(function (data) {
    const content = data.text.replace(/\n/g, ' ');
    return {
      pageCount: data.numpages,
      content,
    };
  });
};

module.exports = { readPdf };
