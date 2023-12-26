package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnalyseTest {

	private Analyse analyse;
    private Map<String, Double> lexicon;

    @BeforeEach
    public void setUp() {
        lexicon = new HashMap<>();
        lexicon.put("good", 1.0);
        lexicon.put("bad", -1.0);
        lexicon.put("nice", 1.5);
        analyse = new Analyse(lexicon);
    }

    @Test
    public void testAnalyseTextPositive() {
        double score = analyse.analyseText("Good and very nice book");
        assertEquals(2.5, score, "The score should correctly reflect the sentiment");
    }

    @Test
    public void testAnalyseTextNegative() {
        double score = analyse.analyseText("It is a bad day");
        assertEquals(-1.0, score, "The score should correctly reflect the sentiment");
    }

    @Test
    public void testAnalyseTextNeutral() {
        double score = analyse.analyseText("This is neutral text");
        assertEquals(0.0, score, "The score should be neutral for neutral text");
    }

    @Test
    public void testAnalyseTextMixed() {
        double score = analyse.analyseText("Good is not bad");
        assertEquals(0.0, score, "The score should balance out for mixed sentiment");
    }
/*  @Test
    public void testAnalyseTextWithPunctuation() {
        double score = analyse.analyseText("Happy, joyous, and bright!");
        assertEquals(2.5, score, "The score should ignore punctuation");
    } */
    
    @Test
    public void testAnalyseTextWithPunctuation() {
        // Assuming the lexicon contains the words "happy", "joyous", and "bright" with scores 1.0, 1.0, and 0.5 respectively
        lexicon.put("happy", 1.0);
        lexicon.put("joyous", 1.0);
        lexicon.put("bright", 0.5);

        double score = analyse.analyseText("Happy, joyous, and bright!");
        assertEquals(2.5, score, "The score should ignore punctuation");
    }
    
    @Test
    public void twoWords() {
    	lexicon.put("very happy", 50.0);
    	lexicon.put("pleasant", 50.0);
    	 double score = analyse.analyseText("It is very happy and pleasant year!");
         assertEquals(100, score, "The score should work with phrases");
    }
    
  
    
}
