package ru.stqa.trainings;

import org.junit.*;
import java.nio.file.*;
import java.io.*;


public class TestBase {

    @Before
    public void setUp(){
        clearDir(MySuite.tempDir.toFile());
        //System.out.println("setUp cleared the directory: " + MySuite.tempDir);
    }

    //@After
    public void tearDown(){
        //System.out.println("tearDown executed");
    }

    protected static boolean deleteFile(File file){
        if(file == null) return false;
        if(file.isDirectory()){
            clearDir(file);
        }
        return file.delete();
    }

    protected static void clearDir(File dir){
        if(dir != null && dir.isDirectory()){
            File[] files = dir.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }
    }
}
