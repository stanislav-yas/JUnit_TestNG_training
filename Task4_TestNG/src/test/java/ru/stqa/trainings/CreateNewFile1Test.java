package ru.stqa.trainings;

import org.testng.annotations.Test;
import java.io.*;

@Test
public class CreateNewFile1Test extends TestBase {

    private String[] fileNames = {"Filename", "ИмяФайла", "Имя файла"};

    @Test(groups = {"positive"}, priority = 1)
    public void test1(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[0]).createNewFile();
            System.out.println("CreateNewFile1Test.test1(positive) result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test1(positive) throws IOException: " + ex.getMessage());
        }

    }

    @Test(groups = {"positive"})
    public void test2(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[1]).createNewFile();
            System.out.println("CreateNewFile1Test.test2(positive) result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test2(positive) throws IOException: " + ex.getMessage());
        }
    }

    @Test(groups = {"negative"})
    public void test3(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[1]).createNewFile();
            System.out.println("CreateNewFile1Test.test3(negative) result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test3(negative) throws IOException: " + ex.getMessage());
        }
    }

    @Test(groups = {"positive"})
    public void test4(){
        boolean result;
        try{
            result = new File(tempDir.toFile(), fileNames[2]).createNewFile();
            System.out.println("CreateNewFile1Test.test4(positive) result = " + result);
        }catch (IOException ex){
            System.out.println("CreateNewFile1Test.test4(positive) throws IOException: " + ex.getMessage());
        }
    }
}
