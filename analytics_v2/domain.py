from dataclasses import dataclass
from enum import Enum
from typing import List
""" Domain Driven Design (DDD) 
See the README for this service (analytics_v2/README.md) to see the design 
"""
class ExerciseType(Enum):
    RUNNING = "Running"
    CYCLING = "Cycling"
    SWIMMING = "Swimming"
    GYM = "Gym"
    OTHER = "Other"

@dataclass(frozen=True)
class ExerciseDetails:
    date: str
    duration: int
    exercise_type: ExerciseType
    description: str

@dataclass(frozen=True)
class Exercise:
    id: str
    exercise_details: ExerciseDetails

@dataclass(frozen=True)
class User:
    username: str

@dataclass(frozen=True)
class Statistics:
    user: User
    exercises: List[Exercise]
