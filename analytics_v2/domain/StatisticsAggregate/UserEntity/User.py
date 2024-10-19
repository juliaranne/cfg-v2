class User:
    def __init__(self, username):
        self._username = username

    @property
    def username(self):
        return self._username
