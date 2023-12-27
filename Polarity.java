package ie.atu.sw;

public class Polarity {
    private FileParser fileParser;
    private Analyse analyse;

    public Polarity(FileParser fileParser, Analyse analyse) {
        this.fileParser = fileParser;
        this.analyse = analyse;
    }

    public double calculateAverageScore(String filePath) throws Exception {
        // Process the file
        fileParser.go(filePath);

        // Get the total number of words
        int numberOfWords = fileParser.getWordsSize();
        int totalScore=60;


        // Calculate the average score
        return numberOfWords > 0 ? totalScore / numberOfWords : 0;
    }
}
