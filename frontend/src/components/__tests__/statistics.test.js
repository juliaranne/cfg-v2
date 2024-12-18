import {fireEvent, render, screen, waitFor, act} from '@testing-library/react';
import Statistics from '../statistics';
import * as useFetch from '../../hooks/useFetch';

jest.mock('../../hooks/useFetch', () => ({
  __esModule: true,
  default: () => ({data: {
        stats: {
            results: {
                exercises: [
                    {
                        exerciseType: "Swimming",
                        totalDuration: 34
                    },
                    {
                        exerciseType: "Running",
                        totalDuration: 23
                    },
                    {
                        exerciseType: "Cycling",
                        totalDuration: 34
                    }
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
  render(<Statistics />)

  const entries = screen.getByTestId('journalList').childNodes;
  expect(entries.length).toBe(2);
})


