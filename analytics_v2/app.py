from flask import Flask, render_template, jsonify, request
from ariadne import QueryType, make_executable_schema, graphql_sync, load_schema_from_path
from ariadne.explorer import ExplorerGraphiQL

app = Flask(__name__)
# This is a template/basic app for the purpose of testing the setup in docker. But, will be removed and replaced in scrum-40

query = QueryType()
type_defs = load_schema_from_path("schema.graphql")
schema = make_executable_schema(type_defs, query)

explorer_html = ExplorerGraphiQL().html(None)

@app.route("/graphql", methods=["GET"])
def graphql_explorer():
    # On GET request serve the GraphQL explorer.
    # You don't have to provide the explorer if you don't want to
    # but keep on mind this will not prohibit clients from
    # exploring your API using desktop GraphQL explorer app.
    return explorer_html, 200

# Create a GraphQL endpoint for executing GraphQL queries
@app.route("/graphql", methods=["POST"])
def graphql_server():
   data = request.get_json()
   success, result = graphql_sync(schema, data, context_value={"request": request})
   status_code = 200 if success else 400
   return jsonify(result), status_code

@app.route('/')
def home():
    return "Welcome to analytics V2"

if __name__ == '__main__':
    app.run(debug=True, port=5051)
