package ie.atu.sw;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu {

	    private String inputFile;
	    private String outputFilePath;
	    private String Lexicons;
	    private Map<String, Double> lexiconMap;


	private Scanner s;

	private boolean keepRunning = true;

	public Menu() {
		s = new Scanner(System.in);
	
	}

	public void start() throws Exception { // taking from student manager app
		while (keepRunning) {
			show();// Display the menu options

			int choice = Integer.parseInt(s.next());// Read user's choice

			// Use a switch statement to perform actions based on the choice
			switch (choice) {
            case 1 -> specifyaTextFileDirectory();
            case 2 -> configureLexicons();
            case 3 -> specifyOutputFileDirectory();
            case 4 -> analyse();
            case 5 -> {keepRunning = false;
            out.println("[INFO] Exiting... Bye!");}
            default -> {
                System.out.println("[Error] Invalid Selection");
                // Add an option to go back to the main menu
                System.out.println("Press 'B' to go back to the main menu or 'Q' to quit:");
                String response = s.next();
                if (response.equalsIgnoreCase("B")) {
                    continue; // Go back to the main menu
                } else if (response.equalsIgnoreCase("Q")) {
                    keepRunning = false; // Quit the program
                }
            }
        }

        
    }
	}

	public String analyse() {
	    String result = "";
	    
	    if (lexiconMap == null) {
	        return "Lexicon is not configured. Please load the lexicon first.";
	    } 
	    try {
	        String text = readTextFromFile(inputFile);
	        TextProcessor analyser = new Analyse(lexiconMap);
	        double score = analyser.processText(text);
	        result = "Sentiment Score: " + score;
	        System.out.println(result);
	    } catch (IOException e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	    return result;
	}





	private String readTextFromFile(String filePath) throws IOException {
	    return new String(Files.readAllBytes(Paths.get(filePath)));
	}


	public void specifyaTextFileDirectory() {
	    System.out.print("Enter the path for the input directory: ");
	    s.nextLine();
	    inputFile = s.nextLine(); // Read user's input for input directory

	    FileParser fileParser = new FileParser(inputFile);
	    try {
	        fileParser.parseInputFile(); // Corrected: No argument needed
	    } catch (Exception e) {
	        System.out.println("Something went wrong during processing the file: " + e.getMessage());
	    }
	}


	public void specifyOutputFileDirectory() {
	    System.out.print("Enter the path for the output file: ");
	    s.nextLine();
	    outputFilePath = s.nextLine(); // Read user's input for output file path

	    try {
	        // Assuming the analyse method returns the sentiment score as a String
	        String analysisResult = analyse();

	        // Now write this result to the specified file
	        Outputter.writeToFile(outputFilePath, analysisResult);
	        System.out.println("Analysis output written to file successfully at " + outputFilePath);
	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}


	public void configureLexicons() {
	    System.out.print("Enter the path for the Lexicons directory: ");
	    s.nextLine();
	    Lexicons = s.nextLine();

	    try {
	        LexiconParsing lexiconParsing = new LexiconParsing(Lexicons);
	        lexiconMap = lexiconParsing.getLexicon(); // Store the loaded lexicon
	    } catch (Exception e) {
	        System.out.println("Error loading lexicon: " + e.getMessage());
	    }
	}



	





	public void show() 
	{
		//You should put the following code into a menu or Menu class
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Sentiment Analyser          *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify a Text File Directory");
		System.out.println("(2) Configure Lexicons");
		System.out.println("(3) Specify an OutputFile Directory (default: ./out.txt)");
		System.out.println("(4) Analyse");
		System.out.println("(5) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-4]>");
		System.out.println();
		

	}
}