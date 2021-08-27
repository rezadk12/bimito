package question2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.TmpFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicSuggestTest {

    protected TmpFile file;

    @Before
    public void setUp() {
        try {
            file = new TmpFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Main.main(new String[]{
                "src/main/resources/users.yaml",
                "src/main/resources/albums.yaml", "1 gholi xy,3 20 xy,6 tehran pop,4 19 malmsteen", file.getPath()
        });
        int[] expectedResults = new int[]{36, 22, 36, 0};
        int[] results = new int[4];
        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(file.getPath()));
            int i = 0;
            while ((strCurrentLine = objReader.readLine()) != null) {
                results[i] = Integer.parseInt(strCurrentLine);
                i++;
            }
            Assert.assertArrayEquals(expectedResults, results);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
