package cucumber;

public enum Url {
    LOGIN("/login"),
    TRACK_EXERCISE("/trackExercise"),
    WEEKLY_JOURNAL("/journal");

    private final String url;

    Url(String url) {
        this.url = url;
    }
}
