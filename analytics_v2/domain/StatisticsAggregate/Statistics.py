from datetime import datetime

class Statistics:
  def __init__(self, user, exercises):
        self.user = user,
        self.exercises = exercises,
        self._created_at = datetime.now()
        self._updated_at = datetime.now()

  @property
  def created_at(self):
      return self._created_at

  @property
  def updated_at(self):
      return self._updated_at

  @property
  def user(self):
      return self._user

  @user.setter
  def user(self, new_user):
      self._user = new_user
      self._updated_at = datetime.now()

  @property
  def exercises(self):
      return self._exercises

  @exercises.setter
  def exercises(self, new_exercises):
      self._exercises = new_exercises
      self._updated_at = datetime.now()  