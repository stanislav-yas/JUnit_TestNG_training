package ru.stqa.trainings;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.*;

public class MyDataProvider {

    @DataProvider(cache = false)
    public static Object[] randomStringProvider(FrameworkMethod testMethod){
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
        }
        if(ds.type() == DataSource.Type.STRING){
            int num = ds.params()[0]; // number of strings
            int limit = ds.params()[1]; // string length limit
            Object[] data = new Object[num];
            for (int i = 0; i < num; i++) {
                data[i] = (RndStrGen.getRndStrAnyLength(ds.value(), limit));
            }
            return data;
        }else{
            throw new Error("Data source type is not supported: " + ds.type());
        }
    }


    @DataProvider(cache = false)
    public static Object[] charsProvider(FrameworkMethod testMethod) throws IOException {
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
        }
        switch (ds.type()) {
            case RESOURCE:
                return loadCharsFromResource(ds.value());

            case FILE:
                return loadCharsFromFile(ds.value());

            default:
                throw new Error("Data source type is not supported: " + ds.type());
        }
    }

    private static Object[] loadCharsFromResource(String value) throws IOException{
        return loadCharsFromInputStream(MyDataProvider.class.getResourceAsStream(value));
    }

    private static Object[] loadCharsFromFile(String value) throws IOException{
        return loadCharsFromInputStream(new FileInputStream(new File(value)));
    }

    private static Object[] loadCharsFromInputStream(InputStream inputStream) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = new ArrayList<>();
        String line = in.readLine();
        while(line != null){
            lines.add(line);
            line = in.readLine();
        }
        inputStream.close();
        return lines.toArray();
    }
}
