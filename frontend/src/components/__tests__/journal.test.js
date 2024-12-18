import {fireEvent, render, screen, waitFor, act} from '@testing-library/react';
import Journal from '../journal';
import * as useFetch from '../../hooks/useFetch';

jest.mock('../../hooks/useFetch', () => ({
  __esModule: true,
  default: () => ({
    data: {
      weekly_stats: {
        results: {
          exercises: [
            {
              description: "Long afternoon hilly run",
              exerciseType: "Running",
              totalDuration: 30
            },
            {
              description: "Weight lifting arms session",
              exerciseType: "Gym",
              totalDuration: 20
            },
          ]
        }
      }
    }
})
}));

beforeEach(() => {
  jest.clearAllMocks();
})


test('should display list of exercises', () => {
  render(<Journal />)

  const entries = screen.getByTestId('journalList').childNodes;
  expect(entries.length).toBe(2);
})

test('should be able to navigate forward by week', () => {
    jest
      .spyOn(useFetch, 'default')
      .mockImplementation(() => ({data:{weekly_stats:{results:{exercises:[]}}}}));

  render(<Journal />)

  fireEvent.click(screen.getByText('Next →'))

  const entries = screen.getByTestId('journalList').childNodes;
  expect(entries.length).toBe(1);
  expect(screen.getByText('No exercises found for this period.')).toBeInTheDocument()
})

test('should be able to navigate backwards by week', () => {
  jest
    .spyOn(useFetch, 'default')
    .mockImplementation(() => ({data:{weekly_stats:{results:{exercises:[{
      description: "20 laps triathlon training",
      exerciseType: "Swimming",
      totalDuration: 45
    }]}}}}));

render(<Journal />)

fireEvent.click(screen.getByText('← Previous'))

const entries = screen.getByTestId('journalList').childNodes;
expect(entries.length).toBe(1);
expect(screen.getByText(/20 laps triathlon training/)).toBeInTheDocument();
});

test('should show error if returned', () => {
  jest
    .spyOn(useFetch, 'default')
    .mockImplementation(() => ({error: 'Internal server error'}));

  render(<Journal />)

  expect(screen.getByText(/Unable to load weekly stats/)).toBeInTheDocument();
})
