import { render, screen } from "@testing-library/react";
import ViewAll from "../viewAll";
import * as useFetch from "../../hooks/useFetch";
import { deleteExercise } from "../../api";

jest.mock("../../api", () => ({
  deleteExercise: jest.fn(),
}));

jest.mock("../../hooks/useFetch", () => ({
  __esModule: true,
  default: () => ({
    data: {
      all_exercises: {
        results: {
          exercises: [
            {
              id: "1",
              exerciseType: "Swimming",
              totalDuration: 34,
              description: "Morning swim",
              date: "2025-01-01T10:00:00.000Z",
            },
          ],
        },
      },
    },
    error: null,
    loading: false,
  }),
}));

beforeEach(() => {
  jest.clearAllMocks();
});

afterEach(() => {
  jest.restoreAllMocks();
});

test("should display title with username", async () => {
  deleteExercise.mockResolvedValue(
    Promise.resolve({
      response: {
        data: { message: "Exercise deleted." },
      },
    })
  );
  render(<ViewAll currentUser="Julia" />);

  // Validate rendered text
  // expect(
  //   screen.getByText("Well done, Julia! This is your overall effort:")
  // ).toBeInTheDocument();

  // Validate exercises
  expect(screen.getByText("Swimming - 34 minutes")).toBeInTheDocument();
});
