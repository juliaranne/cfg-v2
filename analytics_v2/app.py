from dotenv import load_dotenv
from flask import Flask, jsonify, request
from pymongo import MongoClient
from flask_pymongo import PyMongo
from flask_cors import CORS
from ariadne import QueryType, gql, make_executable_schema, graphql_sync, load_schema_from_path
from ariadne.explorer import ExplorerGraphiQL
import os
import logging
from datetime import datetime, timedelta

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "*"}},
     methods="GET,HEAD,POST,OPTIONS,PUT,PATCH,DELETE")
# This is a template/basic app for the purpose of testing the setup in docker. But, will be removed and replaced in scrum-40

load_dotenv()
mongo_uri = os.getenv('MONGO_URI')
mongo_db = os.getenv('MONGO_DB')

client = MongoClient(mongo_uri)
db = client[mongo_db]

query = QueryType()
type_defs = gql(load_schema_from_path("schema.graphql"))

explorer_html = ExplorerGraphiQL().html(None)

@app.route("/graphql", methods=["GET"])
def graphql_explorer():
    # On GET request serve the GraphQL explorer.
    return explorer_html, 200

@app.route("/graphql", methods=["POST"])
def graphql_server():
   data = request.get_json()
   success, result = graphql_sync(schema, data, context_value={"request": request})
   status_code = 200 if success else 400
   return jsonify(result), status_code

@app.route('/')
def home():
    return "Welcome to analytics V2"

@query.field("weekly_stats")
def resolve_stats(obj, info, start, end, username):
    date_format = "%Y-%m-%d"
    try:
        start_date = datetime.strptime(start, date_format)
        end_date = datetime.strptime(end, date_format) + timedelta(days=1)  # Include the whole end day

        logging.info(f"Fetching weekly stats for user from {start_date} to {end_date}")
    except Exception as e:
        logging.error(f"Error parsing dates: {e}")
        return "Invalid date format"

    pipeline = [
        {
            "$match": {
                "username": username,
                "date": {
                    "$gte": start_date,
                    "$lt": end_date
                }
            }
        },
        {
            "$group": {
                "_id": {
                    "exerciseType": "$exerciseType",
                    "description": "$description"
                },
                "totalDuration": {"$sum": "$duration"}
            }
        },
        {
            "$project": {
                "exerciseType": "$_id.exerciseType",
                "description": "$_id.description",
                "totalDuration": "$totalDuration",
                "_id": 0
            }
        }
    ]

    try:
        stats = list(db.exercises.aggregate(pipeline))
        return { "exercises": stats }
    except Exception as e:
        return "An internal error occurred"

# The following needs to come after all resolver functions
schema = make_executable_schema(type_defs, query)

if __name__ == '__main__':
    app.run(debug=True, port=5051)
