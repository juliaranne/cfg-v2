import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { trackExercise, getSentimentMessage } from "../api";
import "bootstrap/dist/css/bootstrap.min.css";
import "./alert_message.css";
import DirectionsRunIcon from "@mui/icons-material/DirectionsRun";
import BikeIcon from "@mui/icons-material/DirectionsBike";
import PoolIcon from "@mui/icons-material/Pool";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import OtherIcon from "@mui/icons-material/HelpOutline";
import FormControlLabel from "@mui/material/FormControlLabel";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import DatePicker from "react-datepicker";
import AlertMessage from "./alert_message";
import "react-datepicker/dist/react-datepicker.css";
import "./track_exercise.css";

const TrackExercise = ({ currentUser }) => {
  const [state, setState] = useState({
    exerciseType: "",
    description: "",
    duration: 0,
    date: new Date(),
  });
  const [message, setMessage] = useState("");
  const [sentiment, setSentiment] = useState("");

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
        exerciseType: "",
        description: "",
        duration: 0,
        date: new Date(),
      });

      setMessage("Activity logged successfully! Well done!");
      setTimeout(() => setMessage(""), 5000);
    } catch (error) {
      console.error("There was an error logging your activity!", error);
      setMessage("Sorry, there was an error logging your activity");
      setTimeout(() => setMessage(""), 5000);
    }
  };

  const closeAlert = () => {
    setSentiment("");
  };

  const showDescriptionAlert = async () => {
    if (state.description) {
      const message = await getSentimentMessage(state.description);
      if (message) {
        setSentiment(message);
      }
    }
  };

  return (
    <div>
      <h3>Track exercise</h3>
      <Form
        onSubmit={handleTrackExercise}
        style={{ maxWidth: "400px", margin: "auto" }}
      >
        <Form.Group controlId="formDate" className="form-margin">
          <Form.Label>Date:</Form.Label>
          <DatePicker
            selected={state.date}
            onChange={(date) => setState({ ...state, date })}
            dateFormat="yyyy/MM/dd"
            id="formDate"
          />
        </Form.Group>
        <Form.Group>
          <Form.Label className="sr-only">Select exercise type</Form.Label>
          <RadioGroup
            row
            className="exercise-type"
            onChange={(e) =>
              setState({ ...state, exerciseType: e.target.value })
            }
          >
            <FormControlLabel
              value="Running"
              id="runningButton"
              control={
                <Radio
                  checkedIcon={<DirectionsRunIcon fontSize="large" />}
                  icon={<DirectionsRunIcon fontSize="large" />}
                  required={true}
                  name="exercise_type"
                  data-testid="RunningBtn"
                />
              }
              label="Select running"
            />
            <FormControlLabel
              value="Cycling"
              id="cyclingButton"
              control={
                <Radio
                  checkedIcon={<BikeIcon fontSize="large" />}
                  icon={<BikeIcon fontSize="large" />}
                  name="exercise_type"
                />
              }
              label="Select cycling"
            />
            <FormControlLabel
              value="Swimming"
              id="swimmingButton"
              control={
                <Radio
                  checkedIcon={<PoolIcon fontSize="large" />}
                  icon={<PoolIcon fontSize="large" />}
                  name="exercise_type"
                />
              }
              label="Select swimming"
            />
            <FormControlLabel
              value="Gym"
              id="gymButton"
              control={
                <Radio
                  checkedIcon={<FitnessCenterIcon fontSize="large" />}
                  icon={<FitnessCenterIcon fontSize="large" />}
                  name="exercise_type"
                />
              }
              label="Record a gym session"
            />
            <FormControlLabel
              value="Other"
              id="otherButton"
              control={
                <Radio
                  checkedIcon={<OtherIcon fontSize="large" />}
                  icon={<OtherIcon fontSize="large" />}
                  name="exercise_type"
                />
              }
              label="Record a different exercise"
            />
          </RadioGroup>
        </Form.Group>
        <Form.Group controlId="description" style={{ marginBottom: "20px" }}>
          <Form.Label>Description:</Form.Label>
          <Form.Control
            as="textarea"
            rows={3}
            required
            value={state.description}
            onChange={(e) =>
              setState({ ...state, description: e.target.value })
            }
          />
        </Form.Group>
        <Form.Group controlId="duration" style={{ marginBottom: "40px" }}>
          <Form.Label>Duration (in minutes):</Form.Label>
          <Form.Control
            type="number"
            required
            value={state.duration}
            min={1}
            onChange={(e) => setState({ ...state, duration: e.target.value })}
          />
        </Form.Group>
        <Button variant="success" type="submit">
          Save activity
        </Button>
      </Form>
      <p
        aria-live="assertive"
        className={`${message ? "fade-in" : ""} response-message`}
      >
        {message}
      </p>
      {sentiment && (
        <AlertMessage message={sentiment} handleClose={closeAlert} />
      )}
    </div>
  );
};

export default TrackExercise;
