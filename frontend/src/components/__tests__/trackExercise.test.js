import {render, screen, fireEvent, waitFor} from '@testing-library/react';
import TrackExercise from '../trackExercise';
import { trackExercise } from '../../api';

jest.mock('../../api', () => ({
  trackExercise: jest.fn(),
}));

beforeEach(() => {
    jest.clearAllMocks();
});

test('should successfully save an exercise', async () => {
    trackExercise.mockResolvedValue(Promise.resolve(
      {
        response: {
          data: {"message":"Exercise added!"}
        }
      }
    ));

    render(<TrackExercise />);

    const exerciseBtn = screen.getByTestId('RunningBtn');
    const descField = screen.getByLabelText('Description:');
    const durationField = screen.getByLabelText('Duration (in minutes):');
    const submitBtn = screen.getByText('Save activity');

    fireEvent.click(exerciseBtn);
    fireEvent.change(descField, {
        target: { value: 'A ten mile run. Good effort' },
    });
    fireEvent.change(durationField, {
        target: { value: 20 },
    });
    fireEvent.click(submitBtn);

    // Assert
    expect(trackExercise).toBeCalledTimes(1)
    await waitFor(() => {
        expect(screen.getByText('Activity logged successfully! Well done!')).toBeInTheDocument();   
    });      
})

test('should display error for unsuccessful response', async () => {
    trackExercise.mockRejectedValue(new Error('Internal server error'));

    render(<TrackExercise />);

    const exerciseBtn = screen.getByTestId('RunningBtn');
    const descField = screen.getByLabelText('Description:');
    const durationField = screen.getByLabelText('Duration (in minutes):');
    const submitBtn = screen.getByText('Save activity');

    fireEvent.click(exerciseBtn);
    fireEvent.change(descField, {
        target: { value: 'Describing my exercise' },
    });
    fireEvent.change(durationField, {
        target: { value: 20 },
    });
    fireEvent.click(submitBtn);

    // Assert
    expect(trackExercise).toBeCalledTimes(1)
    await waitFor(() => {
        expect(screen.getByText('Sorry, there was an error logging your activity')).toBeInTheDocument();   
    });      
})