import React, { useState, useEffect } from 'react';
import useFetch from '../hooks/useFetch';
import { Button } from 'react-bootstrap';
import moment from 'moment';
import './viewAll.css';

const getAllExercisesQuery = (user) => {
  return `
    query {
      all_exercises(username: "${user}") {
        results {
          exercises {
            exerciseType
            description
            totalDuration
            date
          }
        }
        success
        error
      }
    }
  `;
};

const ViewAll = ({ currentUser }) => {
  const { data, error, loading } = useFetch(getAllExercisesQuery(currentUser));

  const exercises = data?.all_exercises?.results?.exercises;

  useEffect(() => {
    console.log("Fetched exercises:", data);
  }, [data]);

  return (
    <div className="view-all-container">
      <h4>All Exercises</h4>
      <br />

      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>Unable to load all exercises.</p>
      ) : (
        <ul data-testid="viewAllList">
          {exercises && exercises.length > 0 ? (
            exercises.map((exercise, index) => (
              <li key={index} className="exercise-view-all-data">
                <strong>{exercise.exerciseType}</strong> - {exercise.totalDuration} minutes
                <br />
                {exercise.description}
                <br />
                <small>
                  {moment(exercise.date).format('YYYY-MM-DD HH:mm:ss')}
                </small>
              </li>
            ))
          ) : (
            <li>No exercises found.</li>
          )}
        </ul>
      )}
    </div>
  );
};

export default ViewAll;
