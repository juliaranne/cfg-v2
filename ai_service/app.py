from flask import Flask, render_template, jsonify, request
import SentimentAnalyser

app = Flask(__name__)

@app.route('/')
def home():
    return "Welcome to the AI service"


@app.route('/get_sentiment_message', methods=['POST'])
def get_sentiment_message():
    description = request.json
    sentiment = SentimentAnalyser.analyze_description(description["sentence"])
    motivational_message = SentimentAnalyser.generate_motivational_message(sentiment)
    return jsonify({"sentiment": sentiment}, {"motivational_message": motivational_message})



if __name__ == '__main__':
    app.run(debug=True, port=5052)