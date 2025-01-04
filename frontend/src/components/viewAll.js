import React, { useState } from 'react';
import useFetch from '../hooks/useFetch';
import { Button } from 'react-bootstrap';
import moment from 'moment';
import './viewAll.css'; // Create a CSS file if needed

// Modify the query to fetch all exercises for a user
const getAllExercisesQuery = (user) => {
    return `
      query {
        all_exercises(username: "${user}") {
          results {
            exercises {
              exerciseType
              description
              totalDuration
            }
          }
          success
          error
        }
      }
    `;
};

const ViewAll = ({ currentUser }) => {
    const { data, error } = useFetch(getAllExercisesQuery(currentUser));

    // Extract exercises from the API response
    const exercises = data?.all_exercises?.results?.exercises;

    return (
        <div className="view-all-container">
            <h4>All Exercises</h4>
            <br />
            <ul data-testid="viewAllList">
                {exercises && exercises.length > 0 ? (
                    exercises.map((exercise, index) => (
                        <li key={index} className="exercise-view-all-data">
                            <strong>{exercise.exerciseType}</strong> - {exercise.totalDuration} minutes
                            <br />
                            {exercise.description}
                        </li>
                    ))
                ) : (
                    <li>No exercises found.</li>
                )}
            </ul>
            {error ? <p>Unable to load all exercises.</p> : ''}
        </div>
    );
};

export default ViewAll;
