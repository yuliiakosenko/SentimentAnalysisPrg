package ie.atu.sw;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetAnalyser extends Analyse {

    public TweetAnalyser(Map<String, Double> lexicon) {
        super(lexicon);
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
}


