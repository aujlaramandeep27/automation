package testCase1;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
	public String suiteName;
	public List<TestResult> passedTests;
	public List<TestResult> failedTests;
	public int numberOfBlockedTests;
	public int numberOfSlowTests;
	
	public TestSuite() {
		this.passedTests = new ArrayList<TestResult>();
		this.failedTests = new ArrayList<TestResult>();
		this.numberOfBlockedTests = 0;
		this.numberOfSlowTests = 0;
	}
	
	public void printResults() {
		System.out.println("Suite Name: " + this.suiteName + "\n");
		
		System.out.println("Number of passed tests: " + this.passedTests.size() + "\n");
		printDetails(this.passedTests);
		
		System.out.println("Number of failed tests: " + this.failedTests.size() + "\n");
		printDetails(this.failedTests);
		
		System.out.println("Number of blocked tests: " + this.numberOfBlockedTests + "\n");
		System.out.println("Number of tests that took more than 10 seconds to execute: " + this.numberOfSlowTests + "\n");
		
		System.out.println("*--*--*");
	}
	
	private void printDetails(List<TestResult> testResults) {
		for(int i=0; i<testResults.size(); i++) {
			TestResult ts = testResults.get(i);
			
			System.out.println("Test Name: " + ts.name);
			System.out.println("Execution Time: " + ts.time);
			System.out.println("Execution Status: " + ts.status + "\n");
		}
	}
}
