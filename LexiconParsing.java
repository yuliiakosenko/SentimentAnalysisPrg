package ie.atu.sw;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;

public class LexiconParsing {
    private Map<String, Double> lexicon;

    public LexiconParsing(String lexiconFilePath) throws IOException {
        this.lexicon = new HashMap<>();
        loadLexicon(lexiconFilePath);
    }

    private void loadLexicon(String lexiconFilePath) throws IOException {
        Files.lines(Path.of(lexiconFilePath)).forEach(line -> {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                try {
                    String phrase = parts[0].toLowerCase();
                    double score = Double.parseDouble(parts[1]);
                    lexicon.put(phrase, score);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid format in lexicon file: " + line);
                }
            }
        });
    }



    public Map<String, Double> getLexicon() {
        return lexicon;
    }
}

