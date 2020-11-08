import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import testCase1.TestCase1;
import testCase2.TestCase2;

public class TestCases {
	
	public static void main(String[] args) {
	
		// Test Case 1
		
		TestCase1 testCase1 = new TestCase1();
		System.out.println("Execution of Test Case 1: \n");
		testCase1.printTestResults();
		
		// Test Case 2
		
		System.out.println("Execution of Test Case 2: \n");
		JUnitCore junit = new JUnitCore();
		  junit.addListener(new TextListener(System.out));
		  Result result = junit.run(TestCase2.class); 
		  if (result.getFailureCount() > 0) {
		    System.out.println("Test failed.");
		    System.exit(1);
		  } else {
		    System.out.println("Test finished successfully.");
		    System.exit(0);
		  }
		
	}

}
