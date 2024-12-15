from flask import Flask, jsonify, request
from flask_cors import CORS

import SentimentAnalyser

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

@app.route('/')
def home():
    return "Welcome to the AI service"


@app.route('/get_sentiment_message', methods=['POST'])
def get_sentiment_message():
    description = request.json
    sentiment = SentimentAnalyser.analyze_description(description["sentence"])
    motivational_message = SentimentAnalyser.get_motivational_message(sentiment)
    return jsonify({"sentiment": sentiment}, {"motivational_message": motivational_message})



if __name__ == '__main__':
    app.run(debug=True, port=5052)