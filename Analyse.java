package ie.atu.sw;

import java.util.Map;

public class Analyse implements TextProcessor {

    private Map<String, Double> lexicon;

    public Analyse(Map<String, Double> lexicon) {
        this.lexicon = lexicon;
    }

    @Override
    public double processText(String text) {
        // Existing implementation of analyseText
        double score = 0;
        text = text.trim().replaceAll("[^a-zA-Z\\s]", "").toLowerCase();

        for (Map.Entry<String, Double> entry : lexicon.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            if (text.contains(key)) {
                score += value;
            }
        }
        
        return score;
    }
}

