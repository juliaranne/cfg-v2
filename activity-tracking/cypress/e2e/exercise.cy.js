/// <reference types="cypress" />

describe("Exercise API", () => {
  let id;

  it("Add a new exercise", () => {
    cy.request({
      method: "POST",
      url: "http://localhost:5300/exercises/add",
      body: {
        username: "testuser",
        exerciseType: "Running",
        description: "Running 5km",
        duration: 30,
        date: new Date(),
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.message).to.eq("Exercise added!");
    });
  });

  it("Retrieve all exercises", () => {
    cy.request("http://localhost:5300/exercises").then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.length.greaterThan(0);
      id = response.body[0]._id;
    });
  });

  it("Retrieve an exercise by id", () => {
    cy.request({
      method: "GET",
      url: `http://localhost:5300/exercises/${id}`,
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body._id).to.eq(id);
    });
  });

  it("Return 400 if exercise not found", () => {
    cy.request({
      method: "GET",
      url: `http://localhost:5300/exercises/wrongId`,
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(400);
    });
  });

  it("Update an exercise by id", () => {
    cy.request({
      method: "PUT",
      url: `http://localhost:5300/exercises/update/${id}`,
      body: {
        username: "testuser",
        exerciseType: "Running",
        description: "Running 30km",
        duration: 120,
        date: new Date(),
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.message).to.eq("Exercise updated!");
      expect(response.body.exercise._id).to.eq(id);
    });
  });

  it("Alert to missing fields when updating an exercise", () => {
    cy.request({
      method: "PUT",
      url: `http://localhost:5300/exercises/update/${id}`,
      body: {
        username: "testuser",
        duration: 120,
        date: new Date(),
      },
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(400);
      expect(response.body.error).to.eq("All fields are required");
    });
  });

  it("Delete an exercise by id", () => {
    cy.request({
      method: "DELETE",
      url: `http://localhost:5300/exercises/${id}`,
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.message).to.eq("Exercise deleted.");
    });
  });
});
