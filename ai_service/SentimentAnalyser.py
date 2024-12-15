import nltk
from nltk.sentiment import SentimentIntensityAnalyzer
import spacy
from transformers import pipeline

# Download NLTK resources
nltk.download('punkt_tab')
nltk.download('vader_lexicon')

# Load the English NLP model from spacy
nlp = spacy.load('en_core_web_sm')

# generator = pipeline("text-generation", model="gpt2")


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

# Todo revisit the below due to inacurrate responses, needs  the prompt finetuning.
# def generate_motivational_message(sentiment):
#     if sentiment == 'Positive':
#         prompt = "Generate a short motivational message to congratulate someone who is happy with their workout. For example: 'Great job on the workout! Keep up the amazing work!'"
#     elif sentiment == 'Negative':
#         prompt = "Generate a short motivational message to encourage someone who is feeling down about their workout. For example: 'Don't give up! Every workout gets you closer to your goals. You've got this!'"
#     else:  # Neutral
#         prompt = "Generate a short motivational message to keep someone motivated who is feeling neutral about their workout. For example: 'Good job today! Stay consistent, and you'll see great results!'"
#
#     # Generate the message
#     generated_text = generator(prompt, max_length=100, num_return_sequences=1)[0]['generated_text']
#
#     # Remove the prompt from the generated text
#     if generated_text.startswith(prompt):
#         generated_text = generated_text[len(prompt):].strip()
#
#     return generated_text

