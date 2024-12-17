import {render, screen} from '@testing-library/react';
import Journal from '../journal';
import useFetch from '../../hooks/useFetch.js';

jest.mock('../../hooks/useFetch', () => ({
  __esModule: true,
  default: () => ({
    data: {
      weekly_stats: {
        results: {
          exercises: [
            {
              description: "lksjfaldskfjalsdf ja;lsdfjk ;",
              exerciseType: "Running",
              totalDuration: 3
            },
            {
              description: "ldkjgaldjkga",
              exerciseType: "Gym",
              totalDuration: 2
            },
          ]
        }
      }
    }
})
}));


test('should display list of exercises', () => {
  render(<Journal />)

  expect(screen.getByText('Running - 3 minutes')).toBeInTheDocument();
})
