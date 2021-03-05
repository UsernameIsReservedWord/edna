import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Main {

    public String getResultFilepath(String path, int rows) {
        String resultPath = null;
        try {
            File sourceFile = new File(path);
            resultPath = sourceFile.getParent().concat("/res_").concat(sourceFile.getName());
            BufferedReader reader = Files.newBufferedReader(sourceFile.toPath());
            Files.deleteIfExists(Paths.get(resultPath));
            FileWriter writer = new FileWriter(resultPath);
            int lineSize = Files.readAllLines(sourceFile.toPath()).size();
            if (rows > lineSize) {
                rows = lineSize;
            }
            for (int i = 0; i < rows; i++) {
                writer.write(reader.readLine().concat(System.lineSeparator()));
            }
            reader.close();
            writer.close();
        } catch (NoSuchFileException e) {
            return "Файл отсутствует";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultPath;
    }

    public String getResultFilepath(String path) {
        return getResultFilepath(path, 10);
    }

    public int getLinesCount(String path) {
        int count = 0;
        try {
            count = Files.readAllLines(Paths.get(path)).size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
