package ru.stqa.trainings;

import com.tngtech.java.junit.dataprovider.DataProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MyDataProvider {

    @DataProvider
    public static Object[] latinNumericStringProvider() {
        int num = 5;
        Object[] data = new Object[num];
        for (int i = 0; i < num; i++) {
            data[i] = (RndStrGen.getRndStrAnyLength(RndStrGen.latinChars + RndStrGen.numericChars, 40));
        }
        return data;
    }

    @DataProvider
    public static Object[] allowSpecCharsProvider() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(MyDataProvider.class.getResourceAsStream("/spec_allow_chars")));
        String line = in.readLine();
        while(line != null){
            lines.add(line);
            line = in.readLine();
        }
        return lines.toArray();
    }

    @DataProvider
    public static Object[] notAllowSpecCharsProvider() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(MyDataProvider.class.getResourceAsStream("/spec_not_allow_chars")));
        String line = in.readLine();
        while(line != null){
            lines.add(line);
            line = in.readLine();
        }
        return lines.toArray();
    }
}
