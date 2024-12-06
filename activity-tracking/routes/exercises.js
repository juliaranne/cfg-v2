const express = require('express');
const router = express.Router();
const Exercise = require('../models/exercise.model');

// GET: Retrieve all exercises
router.get('/', async (req, res) => {
  console.log(JSON.stringify({ action: 'RETRIEVING ALL EXERCISES', timestamp: new Date().toISOString() }));
  try {
    const exercises = await Exercise.find();
    console.log(
        JSON.stringify({
          action: 'RETRIEVED ALL EXERCISES',
          count: exercises.length,
          timestamp: new Date().toISOString()
        })
    );
    res.json(exercises);
  } catch (error) {
    console.error(
        JSON.stringify({
          action: 'ERROR RETRIEVING ALL EXERCISES',
          error: error.message,
          timestamp: new Date().toISOString()
        })
    );
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// POST: Add a new exercise
router.post('/add', async (req, res) => {
  console.log(JSON.stringify({ action: 'ADDING NEW EXERCISE', requestData: req.body, timestamp: new Date().toISOString() }));
  try {
    const { username, exerciseType, description, duration, date } = req.body;

    const newExercise = new Exercise({
      username,
      exerciseType,
      description,
      duration: Number(duration),
      date: Date.parse(date),
    });

    await newExercise.save();
    console.log(
        JSON.stringify({
          action: 'NEW EXERCISE ADDED',
          exercise: newExercise,
          timestamp: new Date().toISOString()
        })
    );
    res.json({ message: 'Exercise added!' });
  } catch (error) {
    console.error(
        JSON.stringify({
          action: 'ERROR ADDING EXERCISE',
          error: error.message,
          timestamp: new Date().toISOString()
        })
    );
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// GET: Retrieve an exercise by ID
router.get('/:id', async (req, res) => {
  console.log(JSON.stringify({ action: 'RETRIEVING EXERCISE', id: req.params.id, timestamp: new Date().toISOString() }));
  try {
    const exercise = await Exercise.findById(req.params.id);
    if (!exercise) {
      console.log(JSON.stringify({ action: 'EXERCISE NOT FOUND', id: req.params.id, timestamp: new Date().toISOString() }));
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }
    console.log(JSON.stringify({ action: 'EXERCISE RETRIEVED', exercise, timestamp: new Date().toISOString() }));
    res.json(exercise);
  } catch (error) {
    console.error(
        JSON.stringify({
          action: 'ERROR RETRIEVING EXERCISE',
          id: req.params.id,
          error: error.message,
          timestamp: new Date().toISOString()
        })
    );
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// DELETE: Delete an exercise by ID
router.delete('/:id', async (req, res) => {
  console.log(JSON.stringify({ action: 'DELETING EXERCISE', id: req.params.id, timestamp: new Date().toISOString() }));
  try {
    const deletedExercise = await Exercise.findByIdAndDelete(req.params.id);
    if (!deletedExercise) {
      console.log(JSON.stringify({ action: 'EXERCISE NOT FOUND FOR DELETION', id: req.params.id, timestamp: new Date().toISOString() }));
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }
    console.log(JSON.stringify({ action: 'EXERCISE DELETED', exercise: deletedExercise, timestamp: new Date().toISOString() }));
    res.json({ message: 'Exercise deleted.' });
  } catch (error) {
    console.error(
        JSON.stringify({
          action: 'ERROR DELETING EXERCISE',
          id: req.params.id,
          error: error.message,
          timestamp: new Date().toISOString()
        })
    );
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// PUT: Update an exercise by ID
router.put('/update/:id', async (req, res) => {
  console.log(JSON.stringify({ action: 'UPDATING EXERCISE', id: req.params.id, requestData: req.body, timestamp: new Date().toISOString() }));
  try {
    const { username, description, duration, date, exerciseType } = req.body;

    if (!username || !description || !duration || !date) {
      console.log(JSON.stringify({ action: 'MISSING FIELDS IN UPDATE REQUEST', requestData: req.body, timestamp: new Date().toISOString() }));
      res.status(400).json({ error: 'All fields are required' });
      return;
    }

    const exercise = await Exercise.findById(req.params.id);
    if (!exercise) {
      console.log(JSON.stringify({ action: 'EXERCISE NOT FOUND FOR UPDATE', id: req.params.id, timestamp: new Date().toISOString() }));
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }

    exercise.username = username;
    exercise.exerciseType = exerciseType;
    exercise.description = description;
    exercise.duration = Number(duration);
    exercise.date = new Date(date);

    await exercise.save();
    console.log(JSON.stringify({ action: 'EXERCISE UPDATED', exercise, timestamp: new Date().toISOString() }));
    res.json({ message: 'Exercise updated!', exercise });
  } catch (error) {
    console.error(
        JSON.stringify({
          action: 'ERROR UPDATING EXERCISE',
          id: req.params.id,
          error: error.message,
          timestamp: new Date().toISOString()
        })
    );
    res.status(500).json({ error: 'An error occurred while updating the exercise' });
  }
});

module.exports = router;
