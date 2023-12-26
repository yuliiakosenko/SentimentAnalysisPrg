package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class FileParser {
    private String inputFile;

    public FileParser(String inputFile) {
        this.inputFile = inputFile;
    }

    public void parseInputFile() throws IOException {
        AtomicInteger lineCount = new AtomicInteger(0); //https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html 
        AtomicInteger wordCount = new AtomicInteger(0);

        Files.lines(Path.of(inputFile)).forEach(line -> {
            lineCount.getAndIncrement();
            String[] words = line.trim().split("\\s+"); //https://www.w3schools.com/java/ref_string_trim.asp
            wordCount.addAndGet(words.length);
        });

        System.out.println("Total lines: " + lineCount);
        System.out.println("Total words: " + wordCount);
    }

 
}

