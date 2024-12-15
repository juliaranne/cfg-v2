from flask import Flask, render_template, jsonify, request

app = Flask(__name__)
# This is a template/basic app for the purpose of testing the setup in docker. But, will be removed and replaced in scrum-40

@app.route('/')
def home():
    return "Welcome to the AI service"

if __name__ == '__main__':
    app.run(debug=True, port=5052)