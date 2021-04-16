package testInterview;

import org.testng.annotations.*;

public class AnnotationsTestNG1 {

    @BeforeMethod
    public void setup() {
        System.out.println("BeforeMethod Annotation");
    }

    @BeforeSuite
    public void setup1() {
        System.out.println("BeforeSuite Annotation");
    }

    @BeforeClass
    public void setup2() {
        System.out.println("                    BeforeClass Annotation");
    }

    @BeforeTest
    public void setup3() {
        System.out.println("                    BeforeTest Annotation");
    }

    @BeforeGroups
    public void setup4() {
        System.out.println("BeforeGroup Annotation");
    }

    @Test
    public void test1() {
        System.out.println("Test Annotation");
    }


    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite Annotation");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("                                    AfterClass Annotation");
    }

    @AfterTest
    public void AfterTest() {
        System.out.println("                AfterTest Annotation");
    }

    @AfterGroups
    public void AfterGroups() {
        System.out.println("AfterGroups Annotation");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("AfterMethod Annotation");
    }


}
