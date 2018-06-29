package ru.stqa.trainings;

import org.testng.annotations.Test;
import java.io.*;

@Test
public class CreateNewFile1Test extends TestBase {

    private String[] fileNames = {"Filename", "ИмяФайла", "Имя файла"};

    public void test1(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[0]).createNewFile();
            System.out.println("CreateNewFile1Test.test1 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test1 throws IOException: " + ex.getMessage());
        }

    }

    public void test2(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[1]).createNewFile();
            System.out.println("CreateNewFile1Test.test2 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test2 throws IOException: " + ex.getMessage());
        }
    }

    public void test3(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[1]).createNewFile();
            System.out.println("CreateNewFile1Test.test3 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test3 throws IOException: " + ex.getMessage());
        }
    }

    public void test4(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[2]).createNewFile();
            System.out.println("CreateNewFile1Test.test4 result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test4 throws IOException: " + ex.getMessage());
        }
    }
}
