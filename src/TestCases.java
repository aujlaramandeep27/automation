import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import testCase1.TestCase1;
import testCase2.TestCase2;
import utils.FileUtils;

public class TestCases {
	
	public static void main(String[] args) {
	
		// Test Case 1
		
		TestCase1 testCase1 = new TestCase1();
		System.out.println("Execution of Test Case 1: \n");
		testCase1.printTestResults();
		
		// Test Case 2
		
		if (System.getProperty("webdriver.chrome.driver") == null) {
			FileUtils fileUtils = new FileUtils();
			String chromedriverPath = fileUtils.getFilePathFromResource("chromedriver");
			System.setProperty("webdriver.chrome.driver", chromedriverPath);
		}
		
		System.out.println("Execution of Test Case 2: \n");
		
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(TestCase2.class);
		
		if (result.getFailureCount() > 0) {
			System.out.println("Tests failed.");
			System.exit(1);
		} else {
			System.out.println("Tests finished successfully.");
			System.exit(0);
		}
		
	}

}
