const pa11y = require("pa11y");

pa11y("http://localhost/signup").then((results) => {
  // Use the results
  console.log(results);
});
