from dataclasses import dataclass
from enum import Enum

ExerciseType = Enum('Running', 'Cycling', 'Swimming', 'Gym', 'Other')


@dataclass(frozen=True)
class ExerciseDetails:
    date: str
    duration: int
    exercise_type: ExerciseType
    description: str
