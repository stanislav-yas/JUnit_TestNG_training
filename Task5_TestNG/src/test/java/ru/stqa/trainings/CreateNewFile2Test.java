package ru.stqa.trainings;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;

@Test
public class CreateNewFile2Test extends TestBase {

    @Test(groups = {"negative"}, alwaysRun = true)
    public void emptyFilename() throws IOException{
        File file = new File(tempDir.toFile(), "");
        boolean result = file.createNewFile();
        Assert.assertFalse(result, "Empty filename created");
    }


    @Test(groups = {"positive"})
    public void fileNameLength()  throws IOException{
        final int MAX_LENGTH = 255;
        final int MIDDLE_LENGTH = 128;
        SoftAssert softAssert = new SoftAssert();
        // empty name of file
        File file = new File(tempDir.toFile(), "");
        boolean result = file.createNewFile();
        softAssert.assertFalse(result,"File with empty name was created");
        // 1-character length of name file creating
        String filename = getRndStr(cyrillicChars, 1);
        file = new File(tempDir.toFile(), filename);
        result = file.createNewFile();
        softAssert.assertTrue(result && file.exists(),"1-character length of name file was not created with name:" + filename);
        // middle length of name file creating
        filename = getRndStr(cyrillicChars + latinChars, MIDDLE_LENGTH);
        file = new File(tempDir.toFile(), filename);
        result = file.createNewFile();
        softAssert.assertTrue(result && file.exists(),"Middle length of name file was not created with name:" + filename);
        // max length of name file creating
        filename = getRndStr(cyrillicChars + latinChars, MAX_LENGTH);
        file = new File(tempDir.toFile(), filename);
        result = file.createNewFile();
        softAssert.assertTrue(result && file.exists(),"Max length of name file has not created with name:" + filename);
        // max+1 length name file creating
        filename = getRndStr(cyrillicChars + latinChars, MAX_LENGTH+1);
        try{
            result = new File(tempDir.toFile(), filename).createNewFile();
            softAssert.assertFalse(result || file.exists(),"Max+1 length of name file was created with name:" + filename);
        }catch (IOException ex){
            // Max+1 length of name file creating throws Exception
        }
        softAssert.assertAll();
    }

}
