import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public String getResultFilepath(String path, int rows) {
        String resultPath = null;
        try {
            File sourceFile = new File(path);
            resultPath = sourceFile.getParent().concat("/res_").concat(sourceFile.getName());
            Files.deleteIfExists(Paths.get(resultPath));
            FileWriter writer = new FileWriter(resultPath);
            int lineSize = getLinesCount(path);
            if (rows > lineSize) {
                rows = lineSize;
            }
            List<String> sourceLines = Files.lines(sourceFile.toPath())
                    .collect(Collectors.toList())
                    .subList(0, rows);
            Collections.shuffle(sourceLines);
            for (String line : sourceLines) {
                writer.write(line.concat(System.lineSeparator()));
            }
            writer.close();
        } catch (NoSuchFileException e) {
            return "Файл отсутствует";
        } catch (IllegalArgumentException e) {
            return "Задано отрицательное кол-во строк";
        }  catch (IOException e) {
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
