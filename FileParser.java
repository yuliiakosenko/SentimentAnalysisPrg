package ie.atu.sw;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class FileParser {
	private static int line = 0;
	
	//private Set<String> words = new ConcurrentSkipListSet<>();
	private Collection<String> words = new ConcurrentLinkedDeque<>();
	
	public void go(String input) throws Exception {
		try (var pool = Executors.newVirtualThreadPerTaskExecutor()){
			Files.lines(Paths.get(input)).forEach(text -> pool.execute(() -> process(text, ++line)));
		}
		
		out.println("Words: " + words.size());
	}
	
	public void process(String text, int line) {
		Arrays.stream(text.split("\\s+")).forEach(w -> words.add(w));
	}


	 public int getWordsSize() {
	        return words.size();
	    }
	
}