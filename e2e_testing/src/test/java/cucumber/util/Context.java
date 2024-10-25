package cucumber.util;

import lombok.Getter;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

@Getter
public class Context {
    /**
     * A class to share context between Cucumber steps
     */
    private static final Duration waitDuration = Duration.ofSeconds(10);

    public static String KEY_EXERCISE_TYPE = "exercise_type";
    public static String KEY_EXERCISE_DURATION = "exercise_duration";
    public static String KEY_EXERCISE_DESCRIPTION = "exercise_description";
    public static String KEY_WEB_ACTIONS = "web_actions";
    public static String KEY_CHROME_DRIVER = "chrome_driver";

    public static final String WEEKLY_JOURNAL_RECORD_TEXT = "%s - %s minutes";

    private static ThreadLocal<Map<String, Object>> data = ThreadLocal.withInitial(HashMap::new);

    public static <T> T get(String key, Function<Object, T> converter) {
        var value = data.get().get(key);
        if (value == null) {
            return null;
        }
        return converter.apply(value);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return get(key, clazz::cast);
    }

    public static Object get(String key) {
        return get(key, Function.identity());
    }

    public static <T> void set(String key, T value) {
        data.get().put(key, value);
    }
}
