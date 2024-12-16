import {render, screen, fireEvent} from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import axios from 'axios';
import Login from '../login';

jest.mock('axios');

test('should login an existing user', async () => {
    axios.post.mockResolvedValue({status: 200});

    render(<Router><Login /></Router>);

    fireEvent.change(screen.getByLabelText('Username'), 'test_username');
    fireEvent.change(screen.getByLabelText('Password'), 'test_password');

    
    // expect(input).toHaveValue('Hello, World!')

    // expect(screen.getByLabelText('Password')).toBeInTheDocument();
})