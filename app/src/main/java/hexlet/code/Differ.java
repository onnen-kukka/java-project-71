package hexlet.code;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> parsedString1 = parse(filepath1);
        Map<String, Object> parsedString2 = parse(filepath2);

        Set<String> allKeys = new TreeSet<>(parsedString1.keySet());
        allKeys.addAll(parsedString2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for(String key : allKeys) {
            if(!parsedString2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(parsedString1.get(key)).append("\n");
            } else if(!parsedString1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(parsedString2.get(key)).append("\n");
            } else if (parsedString1.get(key).equals(parsedString2.get(key))) {
                result.append("    ").append(key).append(": ").append(parsedString1.get(key)).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(parsedString1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(parsedString2.get(key)).append("\n");
            }
        }

        result.append("}\n");
        String finalString = result.toString();
        return finalString;
    }

    public static Map<String, Object> parse(String filepath) throws Exception {
        String jsonString = Files.readString(Paths.get(filepath));
        ObjectMapper objectMapper = new ObjectMapper();
        Map <String, Object> parsedString = objectMapper.readValue(jsonString, Map.class);
        return parsedString;
    }
}

