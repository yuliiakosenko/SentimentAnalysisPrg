package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * The LexiconParsing class is responsible for loading and parsing a lexicon file.
 * The lexicon file is expected to contain phrases and their corresponding scores,
 * which are used for text analysis purposes.
 */
public class LexiconParsing {
    private Map<String, Double> lexicon;

    /**
     * Constructs a LexiconParsing object and initiates the loading of the lexicon file.
     *
     * @param lexiconFilePath The path to the lexicon file.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    public LexiconParsing(String lexiconFilePath) throws IOException {
        this.lexicon = new HashMap<>();
        loadLexicon(lexiconFilePath);
    }

    /**
     * Loads and parses the lexicon file. Each line in the file is expected to be in the format
     * "phrase,score", where 'phrase' is a string and 'score' is a double value.
     *
     * @param lexiconFilePath The path to the lexicon file.
     * @throws IOException if an I/O error occurs reading from the file.
     */
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

    /**
     * Returns the loaded lexicon map.
     *
     * @return A map of phrases to their respective scores.
     */
    public Map<String, Double> getLexicon() {
        return lexicon;
    }
}

