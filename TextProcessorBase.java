package ie.atu.sw;

public abstract class TextProcessorBase implements TextProcessor {

    // Abstract method to process text
    public abstract double processText(String text);

    // Common method to preprocess text
    protected String preprocessText(String text) {
        // Preprocess the text
        text = text.trim().replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
        return text;
    }
}
