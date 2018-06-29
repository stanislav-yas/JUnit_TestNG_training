package ru.stqa.trainings;
import org.testng.annotations.*;
import java.nio.file.*;
import java.io.*;
import java.util.Random;


public class TestBase {

    static Path tempDir = null;
    static Random rnd = new Random();
    static final String latinChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String cyrillicChars = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    @BeforeTest(alwaysRun = true)
    public void setUp() throws IOException{

        tempDir = Files.createTempDirectory("testDir");
        System.out.println("setUp created a directory: " + tempDir);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){

        deleteFile(tempDir.toFile());
        System.out.println("tearDown deleted a directory: " + tempDir);
    }

    void deleteFile(File file){
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

    static String getRndStr(String charSet, int wordLength) {
        StringBuilder sb = new StringBuilder(wordLength);
        for (int i = 0; i < wordLength; i++) {
            int r = rnd.nextInt(charSet.length());
            sb.append(charSet.charAt(r));
        }
        return sb.toString();
    }

    static String getRndStrAnyLength(String charSet, int wordLengthLimit){
        int length;
        do {
            length = rnd.nextInt(wordLengthLimit);
        }while(length == 0); // exclude empty string
        return getRndStr(charSet, length);
    }

}
