import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleTest {

    Main mainTest = new Main();
    public static final String PATH = "src/main/resources/custom-file.txt";

    @Test
    public void fileTest() {
        String res = mainTest.getResultFilepath(PATH);
        Assert.assertEquals(mainTest.getLinesCount(res), 10);
    }

    @Test
    public void maxLinesTest() {
        int sourceSize = mainTest.getLinesCount(PATH);
        String res = mainTest.getResultFilepath(PATH, sourceSize+1);
        Assert.assertEquals(mainTest.getLinesCount(res), sourceSize);
    }

    @Test
    public void fileNotExistTest() {
        String res = mainTest.getResultFilepath("src/main/resources/not-exist-file.txt");
        Assert.assertEquals(res, "Файл отсутствует");
    }

    @Test
    public void emptyFileTest() {
        String res = mainTest.getResultFilepath("src/main/resources/empty.txt");
        Assert.assertEquals(mainTest.getLinesCount(res), 0);
    }

    @Test
    public void zeroLineTest() {
        String res = mainTest.getResultFilepath(PATH, 0);
        Assert.assertEquals(mainTest.getLinesCount(res), 0);
    }

    @Test
    public void negativeAmountOfLinesTest() {
        String res = mainTest.getResultFilepath(PATH, -1);
        Assert.assertEquals(res, "Задано отрицательное кол-во строк");
    }
}
