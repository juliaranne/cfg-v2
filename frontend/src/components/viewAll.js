import React, { useEffect, useState } from "react";
import useFetch from "../hooks/useFetch";
import { deleteExercise } from "../api";
import CloseIcon from "@mui/icons-material/Close";
import CircularProgress from "@mui/material/CircularProgress";
import moment from "moment";
import "./viewAll.css";

const getAllExercisesQuery = (user) => {
  return `
    query {
      all_exercises(username: "${user}") {
        results {
          exercises {
            id
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
  const [exercises, setExercises] = useState([]);
  const [deleteState, setDeleteState] = useState({ id: "", error: "" });

  useEffect(() => {
    setExercises(data?.all_exercises?.results?.exercises);
  }, [data]);

  const filterExercises = (id) => {
    const exerciseCopy = JSON.parse(JSON.stringify(exercises));
    const usersExercises = exerciseCopy.filter(
      (exercise) => exercise.id !== id
    );
    setExercises(usersExercises);
  };

  const handleDelete = async (id) => {
    setDeleteState({ id });
    try {
      await deleteExercise(id);
      filterExercises(id);
    } catch (e) {
      console.log(e);
      setDeleteState({ error: "Sorry, unable to delete your exercise" });
      setTimeout(() => setDeleteState({ id: "", error: "" }), 3000);
    }
  };

  return (
    <div className="view-all-container">
      <h4>All Exercises</h4>
      <br />

      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>Unable to load all exercises.</p>
      ) : (
        <>
          <ul data-testid="viewAllList">
            {exercises && exercises.length > 0 ? (
              exercises.map((exercise, index) => (
                <li key={index} className="exercise-view-all-data">
                  <span>
                    <strong>{exercise.exerciseType}</strong> -{" "}
                    {exercise.totalDuration} minutes
                    <br />
                    {exercise.description}
                    <br />
                    <small>
                      {moment(exercise.date).format("YYYY-MM-DD HH:mm:ss")}
                    </small>
                  </span>
                  <button
                    type="button"
                    className="close-button"
                    onClick={() => handleDelete(exercise.id)}
                    disabled={deleteState.id === exercise.id}
                    title="Delete"
                  >
                    {deleteState.id === exercise.id ? (
                      <>
                        <CircularProgress color="inherit" size="20px" />
                        <span aria-live="assertive" className="sr-only">
                          Deleting
                        </span>
                      </>
                    ) : (
                      <>
                        <span className="sr-only">
                          Delete {exercise.totalDuration} minutes{" "}
                          {exercise.exerciseType}
                        </span>
                        <CloseIcon></CloseIcon>
                      </>
                    )}
                  </button>
                </li>
              ))
            ) : (
              <li>No exercises found.</li>
            )}
          </ul>
          {deleteState.error ? <p>{deleteState.error}</p> : null}
        </>
      )}
    </div>
  );
};

export default ViewAll;
