from dataclasses import dataclass
from typing import List

from analytics_v2.domain.StatisticsAggregate.ExerciseEntity import Exercise
from analytics_v2.domain.StatisticsAggregate.UserEntity import User


@dataclass(frozen=True)
class Statistics:
    user: User
    exercises: List[Exercise]
