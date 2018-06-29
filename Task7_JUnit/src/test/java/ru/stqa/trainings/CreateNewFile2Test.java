package ru.stqa.trainings;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.*;

@RunWith(DataProviderRunner.class)
public class CreateNewFile2Test extends TestBase implements MyCategories{

    @Test
    @Category(Positive.class)
    @UseDataProvider(value = "allowSpecCharsProvider", location = MyDataProvider.class)
    public void specAllowChars(String chr){

        StringBuffer buf = new StringBuffer(RndStrGen.getRndStr(RndStrGen.cyrillicChars, 20));
        int rndOffset = RndStrGen.rng.nextInt(buf.length());
        String filename = buf.insert(rndOffset,chr).toString(); // insert spec char to random position of string
        try{
            File file = new File(MySuite.tempDir.toFile(), filename);
            boolean result = file.createNewFile();
            Assert.assertTrue("File with name containing allowed char has not created:" + filename, result);
            //file.delete();
        }catch (IOException ex){
            Assert.fail("Creating file with name containing allowed char throws Exception:" + ex.getMessage());
        }
    }

    @Test
    @Category({Negative.class, Broken.class})
    @UseDataProvider(value = "notAllowSpecCharsProvider", location = MyDataProvider.class)
    public void specNotAllowChars(String chr){

        StringBuffer buf = new StringBuffer(RndStrGen.getRndStr(RndStrGen.latinChars, 25));
        int rndOffset = RndStrGen.rng.nextInt(buf.length());
        String filename = buf.insert(rndOffset,chr).toString(); // insert spec char to random position of string
        try{
            boolean result = new File(MySuite.tempDir.toFile(), filename).createNewFile();
            Assert.assertFalse("File with name containing not allowed char created:" + filename, result);
        }catch (IOException ex){
            // ok;
        }
    }

}
