package ie.atu.sw;

import java.io.FileWriter;
import java.io.IOException;

public class Outputter {

    public static void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(content);
        }
    }
}