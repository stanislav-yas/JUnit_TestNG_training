package ru.stqa.trainings;
import org.testng.annotations.*;
import java.nio.file.*;
import java.io.*;


public class TestBase {

    static Path tempDir = null;

    @BeforeTest
    public void setUp() throws IOException{

        tempDir = Files.createTempDirectory("testDir");
        System.out.println("setUp created a directory: " + tempDir);

    }

    @AfterTest
    public void tearDown(){

        deleteFile(tempDir.toFile());
        System.out.println("tearDown deleted a directory: " + tempDir);
    }

    protected void deleteFile(File file){
        if(file != null){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File f : files) {
                    deleteFile(f);
                }
            }
            file.delete();
        }
    }
}
