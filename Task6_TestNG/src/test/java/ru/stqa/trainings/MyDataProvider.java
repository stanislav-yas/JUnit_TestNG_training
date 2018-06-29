package ru.stqa.trainings;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[]> latinNumericStringProvider(){

        List<Object[]> data = new ArrayList<>();
        for(int i = 5; i> 0; i--){
            data.add(new String[]{RndStrGen.getRndStrAnyLength(RndStrGen.latinChars + RndStrGen.numericChars, 40)});
        }
        return data.iterator();
    }

    @DataProvider
    public Iterator<Object[]> specAllowCharsProvider() throws IOException {

        List<Object[]> data = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(MyDataProvider.class.getResourceAsStream("/spec_allow_chars")));
        String line = in.readLine();
        while(line != null){
            data.add(new String[]{line});
            line = in.readLine();
        }
        return data.iterator();
    }

    @DataProvider
    public Iterator<Object[]> specNotAllowCharsProvider() throws IOException {

        List<Object[]> data = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(MyDataProvider.class.getResourceAsStream("/spec_not_allow_chars")));
        String line = in.readLine();
        while(line != null){
            data.add(new String[]{line});
            line = in.readLine();
        }
        return data.iterator();
    }
}
