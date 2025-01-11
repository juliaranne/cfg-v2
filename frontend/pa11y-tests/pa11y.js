const pa11y = require("pa11y");
const fs = require("fs");
const { format } = require("@fast-csv/format"); // For CSV formatting
const createRandomString = require("./helpers/generateRandomUsername");

// Write results to CSV
async function writeResultsToCSV(results, filePath) {
  const csvStream = format({ headers: true });
  const writableStream = fs.createWriteStream(filePath);

  writableStream.on("finish", () => {
    console.log(`CSV file written to ${filePath}`);
  });

  csvStream.pipe(writableStream);
  results.forEach((result) => csvStream.write(result));
  csvStream.end();
}

async function runExample(url, actions) {
  try {
    // Test http://example.com/
    const result = await pa11y(url, {
      // Run some actions before the tests
      actions: actions,

      // Log what's happening to the console
      log: {
        debug: console.log,
        error: console.error,
        info: console.log,
      },
      runners: ["htmlcs", "axe"],
    });

    // Output the raw result object
    // console.log(result);
    // writeResultsToCSV(result, "pa11y-results.csv");
    return result;
  } catch (error) {
    // Output an error if it occurred
    console.error(error.message);
  }
}

async function main() {
  const pages = [
    {
      url: "http://localhost/login",
    },
    {
      url: "http://localhost/trackExercise",
      actions: [
        "click element p.mt-3 a",
        `set field #formBasicEmail to ${createRandomString(8)}`,
        "set field #formBasicPassword to password",
        "click element button[type=submit]",
        "wait for url to be http://localhost/trackExercise",
      ],
    },
    {
      url: "http://localhost/journal",
      actions: [
        "click element p.mt-3 a",
        `set field #formBasicEmail to ${createRandomString(8)}`,
        "set field #formBasicPassword to password",
        "click element button[type=submit]",
        "wait for url to be http://localhost/trackExercise",
        "click element .nav-link:nth-of-type(3)",
        "wait for url to be http://localhost/journal",
      ],
    },
  ];

  const results = [];

  for (let i = 0; i < pages.length; i += 1) {
    const result = await runExample(pages[i].url, pages[i].actions);
    result.issues.forEach((issue) => (issue.url = pages[i].url));
    results.push(...result.issues);
  }

  console.log(results);

  writeResultsToCSV(results, "pa11y-results.csv");
}

main();
