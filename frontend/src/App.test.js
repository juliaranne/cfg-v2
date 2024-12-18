import { render, screen, fireEvent, act, waitFor } from '@testing-library/react';
import axios from 'axios';
import App from './App';

jest.mock('axios');
beforeEach(() => {
  jest.clearAllMocks();
});

test('should render fitness app login page', () => {
  render(<App />);
  const siteTitle = screen.getByText(/MLA Fitness App/i);
  expect(siteTitle).toBeInTheDocument();
});

test('should log existing user in', async () => {
  axios.post.mockResolvedValue({
    data: 'User authenticated',
    status: 200
  })

  render(<App />);

  fireEvent.change(screen.getByLabelText('Username'), {
    target: { value: 'cfg_student' },
  });
  fireEvent.change(screen.getByLabelText('Password'), {
    target: { value: '12345678' },
  });
  
  fireEvent.click(screen.getByText('Login'));
  
  await waitFor(() => {
    expect(screen.getByText('Track exercise')).toBeInTheDocument();
  })
});

test('should sign in new user', async () => {
  axios.post.mockResolvedValue({
    data: "User registered successfully!",
    status: 200
  })

  render(<App />);

  fireEvent.click(screen.getByText('Sign up'));
  expect(screen.getByText('Already have an account?')).toBeInTheDocument()

  fireEvent.change(screen.getByLabelText('Username'), {
    target: { value: 'new_cfg_student' },
  });
  fireEvent.change(screen.getByLabelText('Password'), {
    target: { value: '12345678' },
  });
  
  fireEvent.click(screen.getByText('Signup'));
  
  await waitFor(() => {
    expect(screen.getByText('Track exercise')).toBeInTheDocument();
  })
});
