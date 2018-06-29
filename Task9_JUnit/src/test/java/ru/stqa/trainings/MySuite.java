package ru.stqa.trainings;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.experimental.categories.Categories;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;
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

    @ClassRule
    public static TemporaryFolder tmpDir = new TemporaryFolder();

}
