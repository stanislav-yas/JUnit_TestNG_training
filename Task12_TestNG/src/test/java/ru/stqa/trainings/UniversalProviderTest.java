package ru.stqa.trainings;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class UniversalProviderTest extends TestBase{

    @Factory
    public static Object[] tf() {
        return new Object[]{ new UniversalProviderTest("/userData.xls", ResourceType.XLS),
                new UniversalProviderTest("/userData.txt", ResourceType.TXT)};
    }

    public UniversalProviderTest(String resource, ResourceType resourceType) {
        super(resource, resourceType);
    }

    @Test(dataProvider = "loadUserFromResource")
    public void test1(String user, String password) {
        System.out.println(user + ":" + password);
    }
}
