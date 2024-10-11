package cucumber.util;

public enum FitnessAppUrl {
    LOGIN("/login"),
    TRACK_EXERCISE("/trackExercise"),
    WEEKLY_JOURNAL("/journal");

    private final String url;

    FitnessAppUrl(String url) {
        this.url = url;
    }
}
