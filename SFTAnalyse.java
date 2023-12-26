package ie.atu.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.regex.Pattern;

public class SFTAnalyse implements TextProcessor {

    private final Map<String, Double> lexicon;
    private final TextProcessorHelper helper;

    public SFTAnalyse(Map<String, Double> lexicon) {
        this.lexicon = lexicon;
        this.helper = new TextProcessorHelper();
    }

    @Override
    public double processText(String inputFile) {
        double score = 0;
        int phraseCount = 0;

        try {
            helper.go(inputFile); // Process the file with TextProcessorHelper
            ConcurrentLinkedDeque<String> words = helper.getPreprocessedWords();

            for (int i = 0; i < words.size(); i++) {
                for (int j = 1; j <= words.size() - i; j++) {
                    String phrase = String.join(" ", words.stream().skip(i).limit(j).toArray(String[]::new));
                    if (lexicon.containsKey(phrase)) {
                        score += lexicon.get(phrase);
                        phraseCount++;
                        i += j - 1; // Skip words that are part of the phrase
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return phraseCount > 0 ? score / phraseCount : 0;
    }
}



