module.exports = {
  defaults: {
    concurrency: 5,
    standard: "WCAG2AA",
    runners: ["axe"],
  },
  urls: [
    { url: "http://localhost/login" },
    { url: "http://localhost/signup" },
    // {
    //   actions: [
    //     "click element p.mt-3 a",
    //     "set field #formBasicEmail to juliartheuser10",
    //     "set field #formBasicPassword to password",
    //     "click element button[type=submit]",
    //     "wait for url to be http://localhost/trackExercise",
    //   ],
    //   url: "http://localhost/trackExercise",
    // },
    {
      actions: [
        "wait for url to be http://localhost/signup",
        "set field #formBasicEmail to juliartheuser19",
        "set field #formBasicPassword to password",
        "wait for url to be http://localhost/trackExercise",
        "screen capture example.png",
        "click element .nav-link:nth-of-type(3)",
        "wait for url to be http://localhost/journal",
      ],
      url: "http://localhost/journal",
    },
    // {
    //   actions: [
    //     "set field #formUsername to newuser15",
    //     "set field #formPassword to password",
    //     "click element button[type=submit]",
    //     "wait for url to be http://localhost/journal",
    //   ],
    //   url: "http://localhost/journal",
    // },
  ],
};
