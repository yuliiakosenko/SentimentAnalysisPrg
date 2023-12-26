package ie.atu.sw;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TextProcessorHelper extends FileParser {

    public ConcurrentLinkedDeque<String> preprocessedWords = new ConcurrentLinkedDeque<>();

    @Override
    public void process(String text, int line) {
        // Split the text into words and preprocess each word
        Arrays.stream(text.split("\\s+"))
              .map(this::preprocessWord)
              .forEach(preprocessedWords::add);
    }

    String preprocessWord(String word) {
        // Preprocess the word: remove non-alphabetic characters and convert to lowercase
        return word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public ConcurrentLinkedDeque<String> getPreprocessedWords() {
        return preprocessedWords;
    }

 
}

