from flask import Flask, render_template, jsonify, request
from SentimentAnalyser import analyze_description

app = Flask(__name__)

@app.route('/')
def home():
    return "Welcome to the AI service"


@app.route('/get_sentiment_message', methods=['POST'])
def get_sentiment_message():
    description = request.json
    sentiment = analyze_description(description["sentence"])
    return jsonify({"sentiment": sentiment})



if __name__ == '__main__':
    app.run(debug=True, port=5052)