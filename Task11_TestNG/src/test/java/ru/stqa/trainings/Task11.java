package ru.stqa.trainings;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Task11 extends TestBase {

    @Test
    @TempDir(read = true, write = false)
    public void cannotCreateFileInAReadOnlyDir() throws IOException {
        File file = new File(dir.toFile(),"newfile");
        file.createNewFile();
        Assert.assertFalse( file.exists(),"New file has created in read only directory: " + dir.toString());
    }
}
