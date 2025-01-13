import { render } from "@testing-library/react";
import { axe, toHaveNoViolations } from "jest-axe";
import App from "../App";
import TrackExercise from "../components/trackExercise";
import Journal from "../components/journal";
import Statistics from "../components/statistics";

expect.extend(toHaveNoViolations);

it("should render App with no violations", async () => {
  const { container } = render(<App />);
  const results = await axe(container);
  expect(results).toHaveNoViolations();
});

it("should render TrackExercise with no violations", async () => {
  const { container } = render(<TrackExercise />);
  const results = await axe(container);
  expect(results).toHaveNoViolations();
});

it("should render Journal with no violations", async () => {
  const { container } = render(<Journal />);
  const results = await axe(container);
  expect(results).toHaveNoViolations();
});

it("should render Statistics with no violations", async () => {
  const { container } = render(<Statistics />);
  const results = await axe(container);
  expect(results).toHaveNoViolations();
});
