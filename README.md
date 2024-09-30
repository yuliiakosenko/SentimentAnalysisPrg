<h1>Sentiment Analysis Program</h1>

<h2>Overview</h2>

This project implements a <b>Sentiment Analysis</b> system in Java. It takes input text, processes it using a sentiment lexicon, and outputs the sentiment score for the given text, particularly focusing on tweet analysis. The system includes various utilities for file parsing, text processing, and a command-line interface (CLI) with colored output.<br>

<h2>Features</h2>

- <b>Sentiment Analysis</b>: Analyzes the sentiment of input text using a lexicon-based approach.<br>
- <b>File Parsing</b>: Ability to parse input files and process text data.<br>
- <b>Tweet Analysis</b>: Specialized tools for analyzing sentiment in tweets or short text.<br>
- <b>CLI with Colored Output</b>: User-friendly menu-driven interface with colored console output.<br>
- <b>Testing Suite</b>: Unit tests included for key functionalities.<br>

<h2>Project Structure</h2>

- <b>Analyse.java</b>: Contains the logic for performing sentiment analysis.<br>
- <b>AnalyseTest.java</b>: JUnit test class for testing the sentiment analysis logic.<br>
- <b>ConsoleColour.java</b>: Provides methods for colored console outputs for better readability.<br>
- <b>FileParser.java</b>: Handles parsing of input files such as lexicons or datasets.<br>
- <b>LexiconParsing.java</b>: Specific to parsing lexicon files for sentiment analysis.<br>
- <b>Menu.java</b>: Displays and manages the command-line interface.<br>
- <b>Outputter.java</b>: Handles the output of sentiment analysis results, formatting the output for the user.<br>
- <b>Runner.java</b>: The main class responsible for running the application.<br>
- <b>TextProcessor.java</b>: Handles various text-processing operations such as tokenization.<br>
- <b>TextProcessorBase.java</b>: A base class for extending text-processing functionality.<br>
- <b>TweetAnalyser.java</b>: A class focused on analyzing tweets for sentiment.<br>

<h2>Installation</h2>

To get started with the project:<br>

1. Clone the repository:<br>

    ```bash
    git clone <repository-url>
    ```

2. Compile the Java files:<br>

    ```bash
    javac *.java
    ```

3. Run the program:<br>

    ```bash
    java Runner
    ```

<h2>Usage</h2>

The program provides a command-line interface with options for analyzing text, loading files, and viewing results. Follow the on-screen menu instructions to load input files (such as lexicons) and perform sentiment analysis.<br>

<h2>Running Tests</h2>

JUnit is used for testing. To run the tests:<br>

1. Compile the test files:<br>

    ```bash
    javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
    ```

2. Run the tests:<br>

    ```bash
    java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AnalyseTest
    ```

<h2>Future Improvements</h2>

- Integration with machine learning models for improved sentiment accuracy.<br>
- Support for more input formats such as CSV and JSON.<br>
- Integration with real-time social media streams for dynamic tweet analysis.<br>

<h2>License</h2>

This project is licensed under the MIT License. See the `LICENSE` file for details.<br>
