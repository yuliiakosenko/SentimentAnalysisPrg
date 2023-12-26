package ie.atu.sw;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The FileParser class is designed for parsing text files from each line.
 * It can handle large files as well.
 * 
 * Big O is O(n)
 */
public class FileParser {
    private static int line = 0;  // Tracks the number of lines processed

    // Collection to store words, ensuring thread safety
    private Collection<String> words = new ConcurrentLinkedDeque<>();

    /**
     * Processes the specified input file by reading each line and extracting words.
     * It uses a virtual thread pool for concurrent line processing.
     *
     * @param inputFile The path to the input file to be processed.
     * @throws Exception if an I/O error occurs reading from the file or a malformed input is given.
     */
    public void go(String inputFile) throws Exception {
        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(inputFile)).forEach(text -> pool.execute(() -> process(text, ++line)));
        }
        out.println(words);
        out.println(words.size());
    }

    /**
     * Processes a single line of text, splitting it into words and adding them to the collection.
     * This method is called concurrently for each line in the file.
     *
     * @param text The line of text to be processed.
     * @param line The line number of the text in the file.
     */
    public void process(String text, int line) {
        Arrays.stream(text.split("\\s+")).forEach(w -> words.add(w));
    }
}
  
    /* from lesson:
	public void go(String book) throws Exception {
		try (var pool = Executors.newVirtualThreadPerTaskExecutor()){
			Files.lines(Paths.get(book)).forEach(text -> pool.execute(() -> process(text, ++line)));
		}
		out.println(words);
		out.println(words.size());
	}
	
	public void process(String text, int line) {
		Arrays.stream(text.split("\\s+")).forEach(w -> words.add(w));
	}
	
	public static void main(String[] args) throws Exception {
		new VirtualThreadFileParser().go("./shakespeare.txt");
		out.println("Lines: " + line);
	}
	*/


