package ru.stqa.trainings;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.SoftAssertions;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(DataProviderRunner.class)
public class CreateNewFile1Test extends TestBase implements MyCategories{

    @Test
    @Category(Broken.class)
    @Unstable(3)
    public void unstableTest(){
        System.out.println("unstableTest invoked");
        Assert.assertTrue("Method unstableTest still unstable",RndStrGen.rng.nextBoolean());
    }

    @Test
    @Category(Positive.class)
    @UseDataProvider(value = "latinNumericStringProvider", location = MyDataProvider.class)
    public void latinNumericFilename(String filename) throws IOException{
        File file = new File(MySuite.tmpDir.getRoot(), filename);
        boolean result = file.createNewFile();
        Assert.assertTrue("File has not created with name:" + filename, result && file.exists());
    }

    @Test
    @Category(Negative.class)
    public void duplicateFilename() throws IOException{
        String filename = RndStrGen.getRndStrAnyLength(RndStrGen.latinChars, 20);
        File file = new File(MySuite.tmpDir.getRoot(), filename);
        boolean result = file.createNewFile();
        Assert.assertTrue("File has not created with name:" + filename, result && file.exists());
        result = file.createNewFile();
        Assert.assertFalse("CreateNewFile returned 'true' on attempt to create a file with the same name" + filename, result);
    }

    @Test
    @Category(Positive.class)
    //@Ignore
    public void fileNameLength()  throws IOException{
        final int MAX_LENGTH = 255;
        final int MIDDLE_LENGTH = 128;
        SoftAssertions softAssert = new SoftAssertions();
        // empty name of file
        File file = new File(MySuite.tmpDir.getRoot(), "");
        boolean result = file.createNewFile();
        softAssert.assertThat(result ).as("File with empty name was created").isFalse();
        // 1-character length of name file creating
        String filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars, 1);
        file = new File(MySuite.tmpDir.getRoot(), filename);
        result = file.createNewFile();
        softAssert.assertThat(result && file.exists())
                .as("1-character length of name file was not created with name:" + filename).isTrue();
        // middle length of name file creating
        filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MIDDLE_LENGTH);
        file = new File(MySuite.tmpDir.getRoot(), filename);
        result = file.createNewFile();
        softAssert.assertThat(result && file.exists())
                .as("Middle length of name file was not created with name:" + filename).isTrue();
        // max length of name file creating
        filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MAX_LENGTH);
        file = new File(MySuite.tmpDir.getRoot(), filename);
        result = file.createNewFile();
        softAssert.assertThat(result && file.exists())
                .as("Max length of name file has not created with name:" + filename).isTrue();
        // max+1 length name file creating
        filename = RndStrGen.getRndStr(RndStrGen.cyrillicChars + RndStrGen.latinChars, MAX_LENGTH+1);
        try{
            result = new File(MySuite.tmpDir.getRoot(), filename).createNewFile();
            softAssert.assertThat(result || file.exists())
                    .as("Max+1 length of name file was created with name:" + filename).isFalse();
        }catch (IOException ex){
            // Max+1 length of name file creating throws Exception
        }
        softAssert.assertAll();
    }

}
