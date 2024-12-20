import React, {useState} from 'react';
import {Button, Form} from 'react-bootstrap';
import {trackExercise} from '../api';
import {getSentimentMessage} from '../api';
import 'bootstrap/dist/css/bootstrap.min.css';
import './sentiment_message.css';
import IconButton from '@material-ui/core/IconButton';
import DirectionsRunIcon from '@material-ui/icons/DirectionsRun';
import BikeIcon from '@material-ui/icons/DirectionsBike';
import PoolIcon from '@material-ui/icons/Pool';
import FitnessCenterIcon from '@material-ui/icons/FitnessCenter';
import OtherIcon from '@material-ui/icons/HelpOutline';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const TrackExercise = ({currentUser}) => {
    const [state, setState] = useState({
        exerciseType: '',
        description: '',
        duration: 0,
        date: new Date(),
    });
    const [message, setMessage] = useState('');
    const [sentiment, setSentiment] = useState('');

    const handleTrackExercise = async (e) => {
        e.preventDefault();

        const dataToSubmit = {
            username: currentUser,
            ...state,
        };

        try {
            const response = await trackExercise(dataToSubmit);
            console.log(response.data);
            showDescriptionAlert(state.description);

            setState({
                exerciseType: '',
                description: '',
                duration: 0,
                date: new Date(),
            });

            setMessage('Activity logged successfully! Well done!');
            setTimeout(() => setMessage(''), 2000);

        } catch (error) {
            console.error('There was an error logging your activity!', error);
        }
    };


    const showDescriptionAlert = async () => {
        if (state.description) {
            const message = await getSentimentMessage(state.description);
            if (message) {
                setSentiment(message);
                setTimeout(() => setSentiment(''), 5000);
            }
        }
    };

    return (
        <div>
            <h3>Track exercise</h3>
            <Form onSubmit={handleTrackExercise} style={{maxWidth: '400px', margin: 'auto'}}>
                <Form.Group controlId="formDate" className="form-margin">
                    <Form.Label>Date:</Form.Label>
                    <DatePicker
                        selected={state.date}
                        onChange={(date) => setState({...state, date})}
                        dateFormat="yyyy/MM/dd"
                    />
                </Form.Group>
                <div style={{marginBottom: '20px'}}>
                    <IconButton id="runningButton" color={state.exerciseType === 'Running' ? "primary" : "default"}
                                onClick={() => setState({...state, exerciseType: 'Running'})}>
                        <DirectionsRunIcon fontSize="large"/>
                    </IconButton>
                    <IconButton id="cyclingButton" color={state.exerciseType === 'Cycling' ? "primary" : "default"}
                                onClick={() => setState({...state, exerciseType: 'Cycling'})}>
                        <BikeIcon fontSize="large"/>
                    </IconButton>
                    <IconButton id="swimmingButton" color={state.exerciseType === 'Swimming' ? "primary" : "default"}
                                onClick={() => setState({...state, exerciseType: 'Swimming'})}>
                        <PoolIcon fontSize="large"/>
                    </IconButton>
                    <IconButton id="gymButton" color={state.exerciseType === 'Gym' ? "primary" : "default"}
                                onClick={() => setState({...state, exerciseType: 'Gym'})}>
                        <FitnessCenterIcon fontSize="large"/>
                    </IconButton>
                    <IconButton id="otherButton" color={state.exerciseType === 'Other' ? "primary" : "default"}
                                onClick={() => setState({...state, exerciseType: 'Other'})}>
                        <OtherIcon fontSize="large"/>
                    </IconButton>
                </div>
                <Form.Group controlId="description" style={{marginBottom: '20px'}}>
                    <Form.Label>Description:</Form.Label>
                    <Form.Control
                        as="textarea"
                        rows={3}
                        required
                        value={state.description}
                        onChange={(e) => setState({...state, description: e.target.value})}
                    />
                </Form.Group>
                <Form.Group controlId="duration" style={{marginBottom: '40px'}}>
                    <Form.Label>Duration (in minutes):</Form.Label>
                    <Form.Control
                        type="number"
                        required
                        value={state.duration}
                        min={1}
                        onChange={(e) => setState({...state, duration: e.target.value})}
                    />
                </Form.Group>
                <Button variant="success" type="submit">
                    Save activity
                </Button>
            </Form>
            {message && <p style={{color: 'green'}}>{message}</p>}
            <div className={`sentiment active ${sentiment ? 'active' : ''}`} aria-live="polite" id="sentimentMessage"><p className="sentiment__message">Test goes here</p></div>
        </div>
    );
};

export default TrackExercise;
