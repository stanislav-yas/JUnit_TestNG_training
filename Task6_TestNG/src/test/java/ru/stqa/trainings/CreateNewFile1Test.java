package ru.stqa.trainings;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.*;

import java.io.*;
import java.util.*;

@Test
public class CreateNewFile1Test extends TestBase {

    @Test(groups = {"positive"}, priority = 1, dataProviderClass = MyDataProvider.class, dataProvider = "latinNumericStringProvider")
    public void latinNumericFilename(String filename){

        try{
            File file = new File(tempDir.toFile(), filename);
            boolean result = file.createNewFile();
            Assert.assertTrue(result, "File has not created with name:" + filename);
            file.delete();
        }catch (IOException ex){
            Assert.fail("Creating of file with latin-numeric name throws Exception:" + ex.getMessage());
        }
    }


    @Test(groups = {"negative"}, alwaysRun = true)
    public void duplicateFilename(){

        String filename = RndStrGen.getRndStrAnyLength(RndStrGen.latinChars, 20);
        try{
            File file = new File(tempDir.toFile(), filename);
            boolean result = file.createNewFile();
            if(result == true){
                // attempt to create duplicate filename
                result = file.createNewFile();
                Assert.assertFalse(result, "New file with name that already exists  has created:" + filename);
                file.delete();
            }else{
                // something was wrong
                Assert.fail("File has not created  with name:" + filename);
            }
        }catch (IOException ex){
            Assert.fail("Creating file with name that already exists throws Exception:" + ex.getMessage());
        }
    }

    @Test(groups = {"positive"})
    public void fileNameLength(){

        final int MAX_LENGTH = 255;
        final int MIDDLE_LENGTH = 128;
        SoftAssert softAssert = new SoftAssert();
        boolean result;
        // empty name of file
        try{
            result = new File(tempDir.toFile(), "").createNewFile();
            softAssert.assertFalse(result, "File with empty name created");
        }catch (IOException ex){
            softAssert.fail("Empty name of file creating throws Exception:" + ex.getMessage());
        }
        // 1-character length name file creating
        try{
            String filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars, 1);
            File file = new File(tempDir.toFile(), filename);
            result = file.createNewFile();
            softAssert.assertTrue(result, "1-character length name file has not created with name:" + filename);
            file.delete();
        }catch (IOException ex){
            softAssert.fail("1-character length name of file creating throws Exception:" + ex.getMessage());
        }
        // middle length name file creating
        try{
            String filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MIDDLE_LENGTH);
            File file = new File(tempDir.toFile(), filename);
            result = file.createNewFile();
            softAssert.assertTrue(result, "Middle length name file has not created with name:" + filename);
            file.delete();
        }catch (IOException ex){
            softAssert.fail("Middle length name of file creating throws Exception:" + ex.getMessage());
        }
        // max length name file creating
        try{
            String filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MAX_LENGTH);
            File file = new File(tempDir.toFile(), filename);
            result = file.createNewFile();
            softAssert.assertTrue(result, "Max length name file has not created with name:" + filename);
            file.delete();
        }catch (IOException ex){
            softAssert.fail("Max length name of file creating throws Exception:" + ex.getMessage());
        }
        // max+1 length name file creating
        try{
            String filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MAX_LENGTH+1);
            result = new File(tempDir.toFile(), filename).createNewFile();
            softAssert.assertFalse(result, "Max+1 length name file has created with name:" + filename);
        }catch (IOException ex){
            // Max+1 length name of file creating throws Exception
        }
        softAssert.assertAll();
    }

}
