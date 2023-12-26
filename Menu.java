package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private String inputFile;
    private String outputFilePath;
    private String lexiconsFilePath;
    private Map<String, Double> lexiconMap;
    private Scanner scanner;
    private String result;

    public Menu() {
        scanner = new Scanner(System.in);
        lexiconMap = new HashMap<>();
    }

    public void start() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    specifyTextFileDirectory();
                    break;
                case "2":
                    configureLexicons();
                    break;
                case "3":
                	performSFTAnalysis();
                    break;
                case "4":
                    performSaSAnalysis();
                    break;
                case "5":
                    specifyOutputFileDirectory();
                    break;
                case "6":
                    System.out.println("Exiting... Bye!");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("**********************************************************");
        System.out.println("* ATU - Dept. of Computer Science & Applied Physics       *");
        System.out.println("* Virtual Threaded Sentiment Analyser                    *");
        System.out.println("**********************************************************");
        System.out.println("(1) Specify a Text File Directory");
        System.out.println("(2) Configure Lexicons");
        System.out.println("(3) Perform SFT Analysis");
        System.out.println("(4) Perform SaS Analysis");
        System.out.println("(5) Specify an Output File Directory");
        System.out.println("(6) Quit");
        System.out.print("Select an option [1-6]: ");
    }

    private void specifyTextFileDirectory() {
        System.out.print("Enter the path for the input text file: ");
        inputFile = scanner.nextLine();
    }

    private void configureLexicons() {
        System.out.print("Enter the path for the Lexicons file: ");
        lexiconsFilePath = scanner.nextLine();

        try {
            LexiconParsing lexiconParsing = new LexiconParsing(lexiconsFilePath);
            lexiconMap = lexiconParsing.getLexicon();
            System.out.println("Lexicon loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading lexicon: " + e.getMessage());
        }
    }

    public void specifyOutputFileDirectory() {
        System.out.print("Enter the path for the output file: ");
        outputFilePath = scanner.nextLine(); // Use existing scanner

        try {
            // Perform the analysis and get the results
            String analysisResultSaS = performSaSAnalysis();
            String analysisResultSFT = performSFTAnalysis();

            // Write results to the specified file
            Outputter.writeToFile(outputFilePath, analysisResultSaS + "\n" + analysisResultSFT);
            System.out.println("Analysis output written to file successfully at " + outputFilePath);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public String performSaSAnalysis() {
        if (lexiconMap.isEmpty()) {
            return "Lexicon is not configured. Please load the lexicon first.";
        }

        try {
            // Create an instance of TextProcessorHelper
            TextProcessorHelper helper = new TextProcessorHelper();
            
            // Create an instance of SaSAnalyse with the lexicon and helper
            SaSAnalyse analyser = new SaSAnalyse(lexiconMap, helper);

            // Perform the analysis on the input file
            double score = analyser.processText(inputFile);
            result = "Sentiment Score (SaS): " + score;
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            result = "An error occurred during SaS Analysis.";
        }
        return result;
    }



    private String performSFTAnalysis() {
        if (lexiconMap.isEmpty()) {
            return "Lexicon is not configured. Please load the lexicon first.";
        }

        try {
            String text = readTextFromFile(inputFile);
            TextProcessor analyser = new SFTAnalyse(lexiconMap);
            double score = analyser.processText(text);
            result = "Sentiment Score (SFT): " + score;
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            result = "An error occurred during SFT Analysis.";
        }
        return result;
    }

    private String readTextFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }


	}
