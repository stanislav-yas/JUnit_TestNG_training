package ru.stqa.trainings;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.*;

@Test
public class CreateNewFile1Test extends TestBase {

    @Test(groups = {"positive"}, priority = 1)
    public void latinFileName() throws IOException{
        String filename = getRndStrAnyLength(latinChars, rnd.nextInt(30) + 1);
        File file = new File(tempDir.toFile(), filename);
        boolean result = file.createNewFile();
        Assert.assertTrue(result && file.exists(), "File has not created with name:" + filename);
    }

    @Test(groups = {"positive"})
    public void cyrillicFileName() throws IOException{
        String filename = getRndStrAnyLength(cyrillicChars, rnd.nextInt(30) + 1);
        File file = new File(tempDir.toFile(), filename);
        boolean result = file.createNewFile();
        Assert.assertTrue(result && file.exists(), "File has not created with name:" + filename);
    }

    @Test(groups = {"negative"}, alwaysRun = true)
    public void duplicateFilename() throws IOException{
        String filename = getRndStrAnyLength(latinChars + cyrillicChars, 40);
        File file = new File(tempDir.toFile(), filename);
        boolean result = file.createNewFile();
        Assert.assertTrue(result && file.exists(), "File has not created with name:" + filename);
        result = file.createNewFile();
        Assert.assertFalse(result, "CreateNewFile returned 'true' on attempt to create a file with the same name" + filename);
    }

}
