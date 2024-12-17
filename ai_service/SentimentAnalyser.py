import nltk
from nltk.sentiment import SentimentIntensityAnalyzer
import spacy

# Download NLTK resources
nltk.download('punkt_tab')
nltk.download('vader_lexicon')

# Load the English NLP model from spacy
nlp = spacy.load('en_core_web_sm')

def analyze_description(sentence):
    sia = SentimentIntensityAnalyzer()
    sentiment_score = sia.polarity_scores(sentence)
    if sentiment_score['compound'] >= 0.05:
        return 'Positive'
    elif sentiment_score['compound'] <= -0.05:
        return 'Negative'
    else:
        return 'Neutral'

def get_motivational_message(sentiment):
    if sentiment == 'Positive':
        return "Nice work on an amazing workout!"
    elif sentiment == 'Negative':
        return "Next time will be better!"
    else:
        return "Don't lose your spark!"