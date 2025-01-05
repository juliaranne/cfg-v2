import React, { useState, useEffect } from 'react';
import useFetch from '../hooks/useFetch';
import { Button } from 'react-bootstrap';
import moment from 'moment';  // Make sure moment is imported
import './viewAll.css';  // Ensure this CSS exists to style the page properly

// Reuse the query you provided for all exercises
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
  // Make sure you're using useFetch hook to get the query data
  const { data, error, loading } = useFetch(getAllExercisesQuery(currentUser));

  // Extract exercises from the GraphQL data response
  const exercises = data?.all_exercises?.results?.exercises;

  useEffect(() => {
    // Logging the data response for debugging purposes
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
                  {/* Format the date using moment */}
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
