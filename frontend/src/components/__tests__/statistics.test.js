import { render, screen } from '@testing-library/react';
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

test('should display title with username', () => {
  render(<Statistics currentUser="Julia" />)

  expect(screen.getByText('Well done, Julia! This is your overall effort:')).toBeInTheDocument()
})

test('should display list of stats', () => {
    render(<Statistics currentUser="Julia" />)
  
    expect(screen.getByTestId('stats').childNodes.length).toBe(4)
})

test('should show error if returned', () => {
    jest
      .spyOn(useFetch, 'default')
      .mockImplementation(() => ({error: 'Internal server error'}));
  
    render(<Statistics />)
  
    expect(screen.getByText(/Unable to load user statistics/)).toBeInTheDocument();
  })


