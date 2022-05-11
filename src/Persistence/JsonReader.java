package Persistence;

import Model.WordSet;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    public JsonReader(String src){
        this.source = src;
    }

    public WordSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWordSet(jsonObject);
    }

    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private WordSet parseWordSet(JSONObject jsonObject){
        String name = jsonObject.getString("name");
        String rawText = jsonObject.getString("rawText");


        return new WordSet(rawText, name);
    }
}
