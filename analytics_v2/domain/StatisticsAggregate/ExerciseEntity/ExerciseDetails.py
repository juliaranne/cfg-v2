class ExerciseDetails:
    def __init__(self, date, duration, exercise_type, description):
        self._date = date
        self._duration = duration
        self._exercise_type = exercise_type
        self._description = description

    @property
    def date(self):
        return self._date

    @property
    def duration(self):
        return self._duration

    @property
    def exercise_type(self):
        return self._exercise_type

    @property
    def exercise_type(self):
        return self._description
