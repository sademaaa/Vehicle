package util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryParser {

    public static Map<String, String> parse(String query) {
        if (query == null) return Map.of();

        return Stream.of(query.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(
                        a -> a[0],
                        a -> a.length > 1 ? a[1] : ""
                ));
    }
}
