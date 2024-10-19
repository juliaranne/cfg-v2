from dataclasses import dataclass


@dataclass(frozen=True)
class ExerciseDetails:
    date: str
    duration: int
    exercise_type: str  # todo, make enum
    description: str
