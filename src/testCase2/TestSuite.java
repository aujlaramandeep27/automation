package testCase2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestCase2.class})
public class TestSuite {

    @BeforeClass
    public static void globalSetup() {
        System.out.println("Test Suite Setup");
     // Optional. If not specified, WebDriver searches the PATH for chromedriver.
	    System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
     
    }

    @AfterClass
    public static void globalTeardown() {
        System.out.println("Test Suite Teardown");
    }

}