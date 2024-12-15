import nltk
from nltk.sentiment import SentimentIntensityAnalyzer
import spacy
from transformers import pipeline

# Download NLTK resources
nltk.download('punkt_tab')
nltk.download('vader_lexicon')

# Load the English NLP model from spacy
nlp = spacy.load('en_core_web_sm')

generator = pipeline("text-generation", model="gpt2")


def analyze_description(sentence):
    sia = SentimentIntensityAnalyzer()
    sentiment_score = sia.polarity_scores(sentence)
    if sentiment_score['compound'] >= 0.05:
        return 'Positive'
    elif sentiment_score['compound'] <= -0.05:
        return 'Negative'
    else:
        return 'Neutral'


def generate_motivational_message(sentiment):
    if sentiment == 'Positive':
        prompt = "You're doing great with your workout! Keep it up! Here's a motivational quote to fuel your progress: "
    elif sentiment == 'Negative':
        prompt = "You're feeling down about your workout. Don't give up! Here's a motivational quote to keep you going: "
    else:
        prompt = "You're staying steady with your workout. Let's keep that momentum! Here's a quote to keep you on track: "

    generated_text = generator(prompt, max_length=100, num_return_sequences=1)[0][
        'generated_text']  #using gpt-2 to generate a motivational message
    return generated_text
