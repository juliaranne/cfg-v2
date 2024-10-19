from dataclasses import dataclass

from analytics_v2.domain.StatisticsAggregate.ExerciseEntity import ExerciseDetails


@dataclass(frozen=True)
class Exercise:
    id: str
    exercise_details: ExerciseDetails
