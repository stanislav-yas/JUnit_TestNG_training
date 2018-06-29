package ru.stqa.trainings;

import org.testng.Assert;
import org.testng.annotations.*;
import java.io.*;

@Test
public class CreateNewFile2Test extends TestBase {


    @Test(groups = {"positive"}, dataProviderClass = MyDataProvider.class, dataProvider = "specAllowCharsProvider")
    public void specAllowChars(String chr){

        StringBuffer buf = new StringBuffer(RndStrGen.getRndStr(RndStrGen.cyrillicChars, 20));
        int rndOffset = RndStrGen.rng.nextInt(buf.length());
        String filename = buf.insert(rndOffset,chr).toString(); // insert spec char to random position of string
        try{
            File file = new File(tempDir.toFile(), filename);
            boolean result = file.createNewFile();
            Assert.assertTrue(result, "File with name containing allowed char has not created:" + filename);
            file.delete();
        }catch (IOException ex){
            Assert.fail("Creating file with name containing allowed char throws Exception:" + ex.getMessage());
        }
    }



    @Test(groups = {"negative","broken"}, dataProviderClass = MyDataProvider.class, dataProvider = "specNotAllowCharsProvider", alwaysRun = true)
    public void specNotAllowChars(String chr){

        StringBuffer buf = new StringBuffer(RndStrGen.getRndStr(RndStrGen.latinChars, 25));
        int rndOffset = RndStrGen.rng.nextInt(buf.length());
        String filename = buf.insert(rndOffset,chr).toString(); // insert spec char to random position of string
        try{
            boolean result = new File(tempDir.toFile(), filename).createNewFile();
            Assert.assertFalse(result, "File with name containing not allowed char created:" + filename);
        }catch (IOException ex){
            // ok;
        }
    }

}
