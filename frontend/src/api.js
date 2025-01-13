import axios from "axios";

function getUrl() {
  if (process.env.CODESPACES === "true") {
    return `https://${process.env.CODESPACE_NAME}-5300.app.github.dev`;
  } else {
    return `http://localhost:5300`;
  }
}

const baseURL = getUrl();

const api = axios.create({
  baseURL,
});

export const trackExercise = (payload) => api.post(`/exercises/add`, payload);

export const deleteExercise = (id) => api.delete(`/exercises/${id}`);

export const getSentimentMessage = async (description) => {
  try {
    const response = await axios.post(
      "http://127.0.0.1:5052/get_sentiment_message",
      {
        sentence: description,
      }
    );
    const motivationalObject = response.data.find(
      (item) => item.motivational_message
    );
    return motivationalObject ? motivationalObject.motivational_message : null;
  } catch (error) {
    console.error(error);
  }
};
