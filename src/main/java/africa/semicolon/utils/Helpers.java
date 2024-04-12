package africa.semicolon.utils;

public class Helpers {
    public static void validateIfNull(String request) {
        if (request == null) throw new NullPointerException(String.format("%s is not a valid argument", request));
    }
    public static void validateIfEmpty(String request) {
        if (request.isEmpty()) throw new IllegalArgumentException(String.format("%s is empty", request));
    }
}
