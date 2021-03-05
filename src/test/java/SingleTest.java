import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleTest {

    Main mainTest = new Main();
    public static final String PATH = "src/main/resources/custom-file.txt";

    @Test
    public void fileTest() {
        String res = mainTest.getResultFilepath(PATH);
        System.out.println(res);
    }

    @Test
    public void maxLinesTest() {
        String res = mainTest.getResultFilepath(PATH, 50);
    }

    @Test
    public void fileNotExistTest() {
        String res = mainTest.getResultFilepath("src/main/resources/not-exist-file.txt");
        Assert.assertEquals(res, "Файл отсутствует");
    }

    @Test
    public void emptyFileTest() {
        String res = mainTest.getResultFilepath("src/main/resources/empty.txt");
    }

    @Test
    public void zeroLineTest() {
        String res = mainTest.getResultFilepath(PATH, 0);
    }

    @Test
    public void negativeAmountOfLinesTest() {
        String res = mainTest.getResultFilepath(PATH, -1);
    }
}
