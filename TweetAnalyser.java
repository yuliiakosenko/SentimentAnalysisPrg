package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetAnalyser extends Analyse {
    private Map<String, Double> lexiconMap;
    private String inputFile;

    
    public TweetAnalyser(Map<String, Double> lexiconMap, String inputFile) {
        super(lexiconMap);  // Correctly calling Analyse's constructor
        this.lexiconMap = lexiconMap;
        this.inputFile = inputFile;
    }

    public Map<String, Double> processUserTexts(String text) {
        Map<String, Double> userScores = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("(?<=^|\\n)@\\w+");
        Matcher matcher = pattern.matcher(text);

        int start = 0;
        String lastUser = null;
        while (matcher.find()) {
            if (lastUser != null) {
                String tweetText = text.substring(start, matcher.start()).trim();
                double score = super.processText(tweetText);
                userScores.put(lastUser, score);
            }
            lastUser = matcher.group();
            start = matcher.end();
        }

        // Handle the last user's text
        if (lastUser != null) {
            String tweetText = text.substring(start).trim();
            double score = super.processText(tweetText);
            userScores.put(lastUser, score);
        }

        return userScores;
    }

    public String analyse() {
        StringBuilder result = new StringBuilder();

        if (lexiconMap == null) {
            return "Lexicon is not configured. Please load the lexicon first.";
        } 

        try {
            String text = readTextFromFile(inputFile);
            Map<String, Double> userScores = this.processUserTexts(text);

            for (Map.Entry<String, Double> entry : userScores.entrySet()) {
                result.append(entry.getKey()) // Username
                      .append(": ")           // Separator
                      .append(entry.getValue()) // Score
                      .append("\n");         // Newline for next record
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return "Error occurred during analysis.";
        }

        return result.toString();
    }

    private String readTextFromFile(String filePath) throws IOException {
       
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}


