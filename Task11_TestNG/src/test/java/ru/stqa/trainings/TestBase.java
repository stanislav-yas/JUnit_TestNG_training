package ru.stqa.trainings;
import org.testng.annotations.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;


public class TestBase {

    protected Path dir;

    @BeforeMethod
    public void setup(Method method) throws IOException{
        TempDir annotation = method.getAnnotation(TempDir.class);
        if(annotation != null){
            Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
            if(annotation.read()){
                perms.add(PosixFilePermission.OWNER_READ);
                perms.add(PosixFilePermission.GROUP_READ);
                perms.add(PosixFilePermission.OTHERS_READ);
            }
            if(annotation.write()){
                perms.add(PosixFilePermission.OWNER_WRITE);
                perms.add(PosixFilePermission.GROUP_WRITE);
                perms.add(PosixFilePermission.OTHERS_WRITE);
            }
            dir = Files.createTempDirectory("TestDir");
            Files.setPosixFilePermissions(dir, perms);
        }
    }

    @AfterMethod
    public void tearDown(){
        if(dir != null && dir.toFile().exists()){
            deleteFile(dir.toFile());
        }
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
