package ru.stqa.trainings;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.*;
import java.io.*;
import java.util.*;


public class TestBase {

    private String resource;
    private ResourceType resType;
    public enum ResourceType{
        TXT,
        DOC,
        XLS
    }

    protected TestBase(String resource, ResourceType resType) {
        this.resource = resource;
        this.resType = resType;
    }

    @DataProvider
    public Iterator<Object[]> loadUserFromResource()throws Exception {
        List<Object[]> userData = new ArrayList<Object[]>();
        switch (resType){
            case XLS:
                POIFSFileSystem fs = new POIFSFileSystem(TestBase.class.getResourceAsStream(resource));
                HSSFSheet sheet = new HSSFWorkbook(fs).getSheetAt(0);
                for (Row r: sheet) {
                    userData.add(new Object[]{r.getCell(0).toString(),r.getCell(1).toString()});
                }
                fs.close();
                break;
            case TXT:
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        TestBase.class.getResourceAsStream(resource)));
                String line = in.readLine();
                while (line != null) {
                    userData.add(line.split(";"));
                    line = in.readLine();
                }
                in.close();
                break;
                default:
                    throw new Exception("resource file has unsupported format");
        }
        return userData.iterator();
    }
}
