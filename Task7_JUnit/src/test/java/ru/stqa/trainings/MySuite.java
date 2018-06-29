package ru.stqa.trainings;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.*;
import java.nio.file.*;

@Suite.SuiteClasses({
    CreateNewFile1Test.class
    ,CreateNewFile2Test.class
})
//@RunWith(Suite.class)
@RunWith(Categories.class)
@Categories.IncludeCategory({
        MyCategories.Positive.class
        , MyCategories.Negative.class
        , MyCategories.Broken.class
})
//@Categories.ExcludeCategory({})

public class MySuite {

    static Path tempDir = null;

    @BeforeClass
    public static void suiteSetUp() throws IOException {
        tempDir = Files.createTempDirectory("testDir");
        System.out.println("suiteSetUp created the directory: " + tempDir);
    }

    @AfterClass
    public static void suiteTearDown(){
        TestBase.deleteFile(tempDir.toFile());
        System.out.println("suiteTearDown deleted the directory: " + tempDir);
    }
}
