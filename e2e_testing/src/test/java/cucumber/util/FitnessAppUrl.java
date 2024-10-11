package cucumber.util;

import lombok.Getter;

@Getter
public enum FitnessAppUrl {
    LOGIN("/login"),
//    SIGNUP("/signup"),
    TRACK_EXERCISE("/trackExercise"),
    WEEKLY_JOURNAL("/journal");

    private final String url;

    FitnessAppUrl(String url) {
        this.url = url;
    }
}
