module.exports = {
  defaults: {
    concurrency: 5,
    standard: "WCAG2AA",
    runners: ["axe"],
    timout: 90000,
  },
  urls: [
    { url: "http://localhost:3000/login" },
    {
      actions: [
        "set field #formUsername to newuser",
        "set field #formPassword to password",
        "click element button[type=submit]",
        "wait for url to be http://localhost/trackExercise",
      ],
      url: "http://localhost/trackExercise",
    },
    {
      actions: [
        "set field #formUsername to newuser",
        "set field #formPassword to password",
        "click element button[type=submit]",
        "wait for url to be http://localhost/journal",
      ],
      url: "http://localhost/journal",
    },
  ],
};
