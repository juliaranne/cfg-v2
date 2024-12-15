from SentimentAnalyser import analyze_description
import unittest
from unittest.mock import MagicMock
import nltk
from nltk.sentiment import SentimentIntensityAnalyzer
import spacy

# Make sure nltk resources are downloaded during setup
nltk.download('vader_lexicon')


class TestAnalyzeDescription(unittest.TestCase):

    def setUp(self):
        # mock the SentimentIntensityAnalyzer instance
        self.mock_sia = MagicMock(spec=SentimentIntensityAnalyzer)

        # mock the polarity_scores method to return set sentiment scores
        self.mock_sia.polarity_scores.return_value = {'compound': 0.0}

        # create a patch for SentimentIntensityAnalyzer to return our mocked instance
        self.patcher = unittest.mock.patch('SentimentAnalyser.SentimentIntensityAnalyzer', return_value=self.mock_sia)
        self.patcher.start()

        # mock spacy load
        self.mock_nlp = MagicMock(spec=spacy.lang.en.English)
        spacy.load = MagicMock(return_value=self.mock_nlp)

    def tearDown(self):
        self.patcher.stop()

    def test_positive_sentiment_returns_posititve(self):
        self.mock_sia.polarity_scores.return_value = {'compound': 0.8}

        result = analyze_description("I love this new phone!")
        self.assertEqual(result, 'Positive')

    def test_negative_sentiment_returns_negative(self):
        self.mock_sia.polarity_scores.return_value = {'compound': -0.8}

        result = analyze_description("This service is horrible.")
        self.assertEqual(result, 'Negative')

    def test_neutral_sentiment_returns_neutral(self):
        self.mock_sia.polarity_scores.return_value = {'compound': 0.0}

        result = analyze_description("The weather is fine today.")
        self.assertEqual(result, 'Neutral')

    def test_edge_case_neutral_sentiment_returns_neutral(self):
        self.mock_sia.polarity_scores.return_value = {'compound': 0.04}

        result = analyze_description("This is okay.")
        self.assertEqual(result, 'Neutral')

    def test_edge_case_negative_neutral_sentiment_returns_neutral(self):
        self.mock_sia.polarity_scores.return_value = {'compound': -0.04}

        result = analyze_description("This is not bad.")
        self.assertEqual(result, 'Neutral')


if __name__ == '__main__':
    unittest.main()
