package ie.atu.sw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class SFTAnalyseTest {

    private SFTAnalyse analyse;
    private Map<String, Double> lexicon;

    @BeforeEach
    void setUp() {
        lexicon = new HashMap<>();
        lexicon.put("happy", 1.0);
        lexicon.put("sad", -1.0);
        lexicon.put("neutral", 0.0);
        analyse = new SFTAnalyse(lexicon);
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
        lexicon.put("extremely nice", 50.0);
        double score = analyse.processText(createTempFile("It is extremely nice and pleasant year!"));
        assertEquals(50.0, score, "The score should work with phrases");
    }

    private String createTempFile(String content) throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, content);
        tempFile.toFile().deleteOnExit();
        return tempFile.toString();
    }
}


