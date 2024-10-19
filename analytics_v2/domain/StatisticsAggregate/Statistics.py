class Statistics:
  def __init__(self, user, exercises, created_at, updated_at):
        self.user = user,
        self.exercises = exercises,
        # I don't want the following to be changeable, just a record - look at @property
        self.created_at = created_at,
        self.updated_at = updated_at