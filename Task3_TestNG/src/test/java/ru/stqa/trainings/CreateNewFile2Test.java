package ru.stqa.trainings;

import org.testng.annotations.Test;
import java.io.*;

@Test
public class CreateNewFile2Test extends TestBase {

    private String[] fileNames = {"", "File_name",  "<Filename>"};

    public void test1(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[0]).createNewFile();
            System.out.println("CreateNewFile2Test.test1 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile2Test.test1 throws IOException: " + ex.getMessage());
        }

    }

    public void test2(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[1]).createNewFile();
            System.out.println("CreateNewFile2Test.test2 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile2Test.test2 throws IOException: " + ex.getMessage());
        }
    }

    public void test3(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[2]).createNewFile();
            System.out.println("CreateNewFile2Test.test3 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile2Test.test3 throws IOException: " + ex.getMessage());
        }
    }

}
