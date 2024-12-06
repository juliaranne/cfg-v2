import React, { useState, useEffect } from 'react';
import useFetch from '../hooks/useFetch';
import { Button } from 'react-bootstrap';
import moment from 'moment';
import './journal.css';

const getQuery = (start, end, user) => {
  return `
    query {
      weekly_stats(start: "${start}", end: "${end}", username: "${user}") {
        results {
          exercises {
            exerciseType
            description
            totalDuration
          }
        }
      }
    }
  `;
}

const Journal = ({ currentUser }) => {
  const [startDate, setStartDate] = useState(moment().startOf('week').toDate());
  const [endDate, setEndDate] = useState(moment().endOf('week').toDate());
  const {data, error} = useFetch(getQuery(moment(startDate).format('YYYY-MM-DD'), moment(endDate).format('YYYY-MM-DD'), currentUser));

  useEffect(() => {
    getQuery();
  }, [currentUser, startDate, endDate]);

  const goToPreviousWeek = () => {
    setStartDate(moment(startDate).subtract(1, 'weeks').startOf('week').toDate());
    setEndDate(moment(endDate).subtract(1, 'weeks').endOf('week').toDate());
  };

  const goToNextWeek = () => {
    setStartDate(moment(startDate).add(1, 'weeks').startOf('week').toDate());
    setEndDate(moment(endDate).add(1, 'weeks').endOf('week').toDate());
  };

  const exercises = data?.weekly_stats?.results?.exercises;

  return (
    <div className="journal-container">
      <h4>Weekly Exercise Journal</h4>
      <br></br>
      <div className="date-range">
        <Button className="button-small" onClick={goToPreviousWeek}>&larr; Previous</Button>
        <span>{moment(startDate).format('YYYY-MM-DD')} to {moment(endDate).format('YYYY-MM-DD')}</span>
        <Button className="button-small" onClick={goToNextWeek}>Next &rarr;</Button>
        </div>
      <ul>
        {exercises && exercises.length > 0 ? (
          exercises.map((exercise, index) => (
            <li key={index} className="exercise-journal-data">
              {exercise.exerciseType} - {exercise.totalDuration} minutes<br />
              {exercise.description}
            </li>
          ))
        ) : (
          <li>No exercises found for this period.</li>
        )}
      </ul>
      {error ? <p>Unable to load weekly stats</p> : ''}
    </div>
  );
};

export default Journal;