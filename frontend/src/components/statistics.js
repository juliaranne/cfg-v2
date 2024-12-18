import React from 'react';
import useFetch from '../hooks/useFetch';
import './statistics.css';

const getQuery = (user) => {
  return `
    query {
      stats(username: "${user}") {
        results {
          exercises {
            exerciseType
            totalDuration
          }
        }
      }
    }
  `;
}

const Statistics = ({ currentUser }) => {
  const {data, error} = useFetch(getQuery(currentUser));

  const userData = data?.stats?.results;

  return (
    <div data-testid="stats" className="stats-container">
      <h4>Well done, {currentUser}! This is your overall effort:</h4>
      {userData ? (
        userData.exercises.map((item, index) => (
          <div key={index} className="exercise-data">
            <div><strong>{item.exerciseType}</strong></div>
            <div>Total Duration: {item.totalDuration} min</div>
          </div>
        ))
      ) : (
        <p>No data available</p>
      )}
      {error ? <p>Unable to load user statistics</p> : ''}
    </div>
  );
};

export default Statistics;
