package ie.atu.sw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Outputter {

    public static void writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); 
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        }
    }
}
