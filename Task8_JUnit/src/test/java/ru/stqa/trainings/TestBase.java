package ru.stqa.trainings;

import org.junit.*;
import org.junit.rules.ExternalResource;

import java.nio.file.*;
import java.io.*;


public class TestBase {

    @Rule
    public ExternalResource ClearDirRule = new ExternalResource() {

        @Override
        protected void before() throws Throwable {
            clearDir(MySuite.tmpDir.getRoot());
            //System.out.println("setUp cleared the directory: " + MySuite.tmpDir.getRoot());
        }
    };

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
