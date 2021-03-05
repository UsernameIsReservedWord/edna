import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleTest {

    static String getResultFilepath(String path, int rows) {
        String resultPath = null;
        try {
            File sourceFile = new File(path);
            resultPath = sourceFile.getParent().concat("/res_").concat(sourceFile.getName());
            List<String> outputList = new ArrayList<>();
            List<String> sourceList = Files.lines(sourceFile.toPath()).collect(Collectors.toList());
            IntStream.range(0, rows).forEach(i -> outputList.add(sourceList.get(i)));
            Files.deleteIfExists(Paths.get(resultPath));
            FileWriter writer = new FileWriter(resultPath);
            outputList.forEach(line -> {
                try {
                    writer.write(line.concat(System.lineSeparator()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultPath;
    }

    static String getResultFilepath(String path) {
        return getResultFilepath(path, 10);
    }

    public static void main(String[] args) {
        System.out.println(getResultFilepath("src/main/resources/custom-file.txt", 10));
    }
}
