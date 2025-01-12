import pa11y from "pa11y";
import fs from "fs";
import { format } from "@fast-csv/format";
import createRandomString from "./helpers/generateRandomUsername.js";

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

async function runPa11yTests(url, actions) {
  try {
    const result = await pa11y(url, {
      actions: actions,
      log: {
        debug: console.log,
        error: console.error,
        info: console.log,
      },
      runners: ["htmlcs"],
    });

    return result;
  } catch (error) {
    console.error(error.message);
  }
}

async function pa11ySetup() {
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
    const result = await runPa11yTests(pages[i].url, pages[i].actions);
    result.issues.forEach((issue) => (issue.url = pages[i].url));
    results.push(...result.issues);
  }

  writeResultsToCSV(results, "pa11y-results.csv");
}

pa11ySetup();
