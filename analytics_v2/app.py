from dotenv import load_dotenv
from flask import Flask, jsonify, request
from pymongo import MongoClient
from flask_pymongo import PyMongo
from flask_cors import CORS
from ariadne import QueryType, gql, make_executable_schema, graphql_sync, load_schema_from_path
from ariadne.explorer import ExplorerGraphiQL
import os

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

# Exercises resolver
@query.field("exercises")
def resolve_exercises(*_):
    exercises = db.exercises.find()
    exercises_list = list(exercises)
    return exercises_list

# The following needs to come after all resolver functions
schema = make_executable_schema(type_defs, query)

if __name__ == '__main__':
    app.run(debug=True, port=5051)
