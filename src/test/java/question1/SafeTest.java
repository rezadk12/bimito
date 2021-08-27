package question1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.TmpFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SafeTest {

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
                "123",
                "241356789,987546231,956874231", file.getPath()
        });
        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(file.getPath()));
            while ((strCurrentLine = objReader.readLine()) != null) {
                Assert.assertEquals(7, Integer.parseInt(strCurrentLine));
            }
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
