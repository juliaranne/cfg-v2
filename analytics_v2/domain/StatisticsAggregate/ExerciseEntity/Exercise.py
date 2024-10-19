class Exercise:
    def __init__(self, id, exercise_details):
        self._id = id
        self._exercise_details = exercise_details

    @property
    def id(self):
        return self._id

    @property
    def exercise_details(self):
        return self._exercise_details
