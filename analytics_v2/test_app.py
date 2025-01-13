import pytest
from unittest.mock import patch, MagicMock
from app import app, schema

@pytest.fixture

def client():
    app.config["TESTING"] = True
    with app.test_client() as client:
        yield client

@patch("app.db")
def test_home(mock_db, client):
    """Test the home route."""
    response = client.get("/")
    assert response.status_code == 200
    assert response.data.decode() == "Welcome to analytics V2"

def test_graphql_explorer(client):
    """Test the GraphQL Explorer route."""
    response = client.get("/graphql")
    assert response.status_code == 200
    assert "GraphiQL" in response.data.decode()

@patch("app.db")
def test_graphql_stats(mock_db, client):
    """Test the `stats` query."""
    mock_collection = MagicMock()
    mock_collection.aggregate.return_value = [
        {
            "username": "testuser",
            "exercises": [
                {"exerciseType": "Running", "totalDuration": 60},
                {"exerciseType": "Cycling", "totalDuration": 30}
            ]
        }
    ]
    mock_db.exercises = mock_collection

    query = {
        "query": "query($username: String!) { stats(username: $username) { results { username exercises { exerciseType totalDuration } } success } }",
        "variables": {"username": "testuser"}
    }

    response = client.post("/graphql", json=query)
    data = response.get_json()
    assert response.status_code == 200
    assert data["data"]["stats"]["success"] is True

@patch("app.db")
def test_graphql_weekly_stats(mock_db, client):
    """Test the `weekly_stats` query."""
    mock_collection = MagicMock()
    mock_collection.aggregate.return_value = [
        {"exerciseType": "Running", "description": "Morning Run", "totalDuration": 40},
        {"exerciseType": "Cycling", "description": "Evening Ride", "totalDuration": 50}
    ]
    mock_db.exercises = mock_collection

    query = {
        "query": "query($username: String!, $start: String!, $end: String!) { weekly_stats(username: $username, start: $start, end: $end) { results { exercises { exerciseType description totalDuration } } success } }",
        "variables": {"username": "testuser", "start": "2023-01-01", "end": "2023-01-07"}
    }

    response = client.post("/graphql", json=query)
    data = response.get_json()
    assert response.status_code == 200
    assert data["data"]["weekly_stats"]["success"] is True
    assert len(data["data"]["weekly_stats"]["results"]["exercises"]) == 2

@patch("app.db")
def test_graphql_all_exercises(mock_db, client):
    """Test the `all_exercises` query."""
    mock_collection = MagicMock()
    mock_collection.find.return_value = [
        {"_id": "1", "exerciseType": "Running", "description": "Morning Run", "duration": 40, "date": "2023-01-01"},
        {"_id": "2", "exerciseType": "Cycling", "description": "Evening Ride", "duration": 50, "date": "2023-01-02"}
    ]
    mock_db.exercises = mock_collection

    query = {
        "query": "query($username: String!) { all_exercises(username: $username) { results { exercises { id exerciseType description totalDuration date } } success } }",
        "variables": {"username": "testuser"}
    }

    response = client.post("/graphql", json=query)
    data = response.get_json()
    assert response.status_code == 200
    assert data["data"]["all_exercises"]["success"] is True
    assert len(data["data"]["all_exercises"]["results"]["exercises"]) == 2

