package ie.atu.sw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class AnalyseTest {

    private Analyse analyse;
    private Map<String, Double> lexicon;

    @BeforeEach
    void setUp() {
        lexicon = new HashMap<>();
        lexicon.put("happy", 1.0);
        lexicon.put("sad", -1.0);
        analyse = new Analyse(lexicon);
    }

    @Test
    void testPositiveSentiment() {
        double score = analyse.processText("I am happy");
        assertEquals(1.0, score, "The score should be positive for positive sentiment");
    }

    @Test
    void testNegativeSentiment() {
        double score = analyse.processText("I am sad");
        assertEquals(-1.0, score, "The score should be negative for negative sentiment");
    }

    @Test
    void testNeutralSentiment() {
        double score = analyse.processText("I am neutral");
        assertEquals(0.0, score, "The score should be neutral for neutral sentiment");
    }

    @Test
    void testEmptyString() {
        double score = analyse.processText("");
        assertEquals(0.0, score, "The score should be zero for an empty string");
    }

    @Test
    void testPunctuationHandling() {
        double score = analyse.processText("happy, joyful, and elated!");
        assertEquals(1.0, score, "The score should ignore punctuation and calculate correctly");
    }
    @Test
    public void twoWords() {
    	lexicon.put("very nice", 50.0);
    	lexicon.put("pleasant", 50.0);
    	 double score = analyse.processText("It is very nice and pleasant year!");
         assertEquals(100, score, "The score should work with phrases");
    }
}

