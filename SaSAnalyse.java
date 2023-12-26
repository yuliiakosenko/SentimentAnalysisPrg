package ie.atu.sw;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SaSAnalyse implements TextProcessor { // Assumes TextProcessor is an interface

    private final Map<String, Double> lexicon;
    private final TextProcessorHelper helper;

    public SaSAnalyse(Map<String, Double> lexicon, TextProcessorHelper helper) {
        this.lexicon = lexicon;
        this.helper = helper;
    }

    @Override
    public double processText(String inputFile) {
        double score = 0;

        try {
            helper.go(inputFile); // Process the file with TextProcessorHelper
            ConcurrentLinkedDeque<String> words = helper.getPreprocessedWords();

            // Convert the deque to an array for easier access
            String[] wordsArray = words.toArray(new String[0]);
            
            // Iterate through the wordsArray to check for phrases
            for (int i = 0; i < wordsArray.length; i++) {
                for (int j = i; j < wordsArray.length; j++) {
                    String potentialPhrase = String.join(" ", Arrays.copyOfRange(wordsArray, i, j + 1)).toLowerCase();
                    if (lexicon.containsKey(potentialPhrase)) {
                        score += lexicon.get(potentialPhrase);
                        i = j; // Skip the next words that are part of the phrase
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as required
        }

        return score;
    }
}


