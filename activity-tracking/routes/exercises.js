const express = require('express');
const router = express.Router();
const Exercise = require('../models/exercise.model');

// GET: Retrieve all exercises
router.get('/', async (req, res) => {
  console.log('RETRIEVING ALL EXERCISES');
  try {
    const exercises = await Exercise.find();
    console.log(`RETRIEVED ${exercises.length} EXERCISES`);
    res.json(exercises);
  } catch (error) {
    console.error('ERROR RETRIEVING ALL EXERCISES:', error.message);
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// POST: Add a new exercise
router.post('/add', async (req, res) => {
  console.log('ADDING NEW EXERCISE:', req.body);
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
    console.log('NEW EXERCISE ADDED:', newExercise);
    res.json({ message: 'Exercise added!' });
  } catch (error) {
    console.error('ERROR ADDING EXERCISE:', error.message);
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// GET: Retrieve an exercise by ID
router.get('/:id', async (req, res) => {
  console.log(`RETRIEVING EXERCISE WITH ID: ${req.params.id}`);
  try {
    const exercise = await Exercise.findById(req.params.id);
    if (!exercise) {
      console.log(`EXERCISE NOT FOUND WITH ID: ${req.params.id}`);
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }
    console.log('EXERCISE RETRIEVED:', exercise);
    res.json(exercise);
  } catch (error) {
    console.error(`ERROR RETRIEVING EXERCISE WITH ID ${req.params.id}:`, error.message);
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// DELETE: Delete an exercise by ID
router.delete('/:id', async (req, res) => {
  console.log(`DELETING EXERCISE WITH ID: ${req.params.id}`);
  try {
    const deletedExercise = await Exercise.findByIdAndDelete(req.params.id);
    if (!deletedExercise) {
      console.log(`EXERCISE NOT FOUND FOR DELETION WITH ID: ${req.params.id}`);
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }
    console.log('EXERCISE DELETED:', deletedExercise);
    res.json({ message: 'Exercise deleted.' });
  } catch (error) {
    console.error(`ERROR DELETING EXERCISE WITH ID ${req.params.id}:`, error.message);
    res.status(400).json({ error: 'Error: ' + error.message });
  }
});

// PUT: Update an exercise by ID
router.put('/update/:id', async (req, res) => {
  console.log(`UPDATING EXERCISE WITH ID: ${req.params.id}`);
  try {
    const { username, description, duration, date, exerciseType } = req.body;

    if (!username || !description || !duration || !date) {
      console.log('MISSING FIELDS IN UPDATE REQUEST:', req.body);
      res.status(400).json({ error: 'All fields are required' });
      return;
    }

    const exercise = await Exercise.findById(req.params.id);
    if (!exercise) {
      console.log(`EXERCISE NOT FOUND FOR UPDATE WITH ID: ${req.params.id}`);
      res.status(404).json({ error: 'Exercise not found' });
      return;
    }

    exercise.username = username;
    exercise.exerciseType = exerciseType;
    exercise.description = description;
    exercise.duration = Number(duration);
    exercise.date = new Date(date);

    await exercise.save();
    console.log('EXERCISE UPDATED:', exercise);
    res.json({ message: 'Exercise updated!', exercise });
  } catch (error) {
    console.error(`ERROR UPDATING EXERCISE WITH ID ${req.params.id}:`, error.message);
    res.status(500).json({ error: 'An error occurred while updating the exercise' });
  }
});

module.exports = router;
