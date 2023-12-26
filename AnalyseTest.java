package ie.atu.sw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class AnalyseTest {

    private SaSAnalyse analyse;
    private Map<String, Double> lexicon;
    private TextProcessorHelper helper;

    @BeforeEach
    void setUp() {
        lexicon = new HashMap<>();
        lexicon.put("happy", 1.0);
        lexicon.put("sad", -1.0);
        helper = new TextProcessorHelper();
        analyse = new SaSAnalyse(lexicon, helper);
    }

    @Test
    void testPositiveSentiment() throws IOException {
        double score = analyse.processText(createTempFile("I am happy"));
        assertEquals(1.0, score, "The score should be positive for positive sentiment");
    }

    @Test
    void testNegativeSentiment() throws IOException {
        double score = analyse.processText(createTempFile("I am sad"));
        assertEquals(-1.0, score, "The score should be negative for negative sentiment");
    }

    @Test
    void testNeutralSentiment() throws IOException {
        double score = analyse.processText(createTempFile("I am neutral"));
        assertEquals(0.0, score, "The score should be neutral for neutral sentiment");
    }

    @Test
    void testEmptyString() throws IOException {
        double score = analyse.processText(createTempFile(""));
        assertEquals(0.0, score, "The score should be zero for an empty string");
    }

    @Test
    void testPunctuationHandling() throws IOException {
        double score = analyse.processText(createTempFile("happy, joyful, and elated!"));
        assertEquals(1.0, score, "The score should ignore punctuation and calculate correctly");
    }

    @Test
    public void twoWords() throws IOException {
        lexicon.put("very nice", 50.0);
        
        double score = analyse.processText(createTempFile("It is very nice and pleasant year!"));
        assertEquals(50, score, "The score should work with phrases");
    }

    private String createTempFile(String content) throws IOException {
        Path tempFile = Files.createTempFile(null, null);
        Files.writeString(tempFile, content);
        tempFile.toFile().deleteOnExit();
        return tempFile.toString();
    }
}


