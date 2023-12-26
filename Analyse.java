package ie.atu.sw;

import java.util.Map;
import java.util.Scanner;

public class Analyse{

    private Map<String, Double> lexicon;

    public Analyse(Map<String, Double> lexicon) {
        this.lexicon = lexicon;
    }
    
    

    public double analyseText(String text) {
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

