from flask import Flask, render_template, jsonify, request
from ariadne import QueryType, make_executable_schema, graphql_sync, load_schema_from_path

app = Flask(__name__)
# This is a template/basic app for the purpose of testing the setup in docker. But, will be removed and replaced in scrum-40

@app.route('/')
def home():
    return "Welcome to analytics V2"

if __name__ == '__main__':
    app.run(debug=True, port=5051)
