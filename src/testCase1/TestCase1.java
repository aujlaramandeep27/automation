package testCase1;

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.google.common.base.Strings;


public class TestCase1 {
	
	public static void main(String[] args) {
		
		FileUtils fileUtils = new FileUtils();
		
	    try (InputStream is = fileUtils.getFileFromResourceAsStream("test_results.json"); 
	    	     Reader rd = new InputStreamReader(is, "UTF-8"); ){
	    	
	       Object obj = new JSONParser().parse(rd);
	       JSONObject jo = (JSONObject)obj;
	       
	       List<TestSuite> testSuitesList = getTestSuites(jo);
	       
	       for(int i=0; i<testSuitesList.size(); i++) {
	    	   testSuitesList.get(i).printResults();
	       }
	       
	    } catch(Exception e) {
	       e.printStackTrace();
	    }
	}
	
	public static List<TestSuite> getTestSuites(JSONObject jo) {
		
		JSONArray testSuites = (JSONArray)jo.get("test_suites");
	       
		Iterator itr = testSuites.iterator();
       
		List<TestSuite> testSuitesList = new ArrayList<TestSuite>();
		
		while (itr.hasNext()) {
			JSONObject ts = (JSONObject)itr.next();
			testSuitesList.add(getTestSuite(ts));
		}
		
		return testSuitesList;
	}
	
	public static TestSuite getTestSuite(JSONObject jo) {
		TestSuite testSuite = new TestSuite();
  	  
  	  	// Get Suite Name 
  	  	testSuite.suiteName = ((String)jo.get("suite_name")).trim();
  	  
  	  	// Get Results 
  	  	List<TestResult> testResultsList = getTestResults(jo);
  	  	
	      
  	  	// Sort Results
  	  	testResultsList.sort((TestResult r1, TestResult r2)->r1.name.compareTo(r2.name));
	      
  	  	//Print Result
  	  	for(int i=0; i<testResultsList.size(); i++) {
  		  
  	  		TestResult tr = testResultsList.get(i);
		  
  	  		if(tr.status.equals("pass")) {
  	  			testSuite.passedTests.add(tr);
  	  		} else if(tr.status.equals("fail")) {
  	  			testSuite.failedTests.add(tr);
  	  		} else if(tr.status.equals("blocked")) {
  	  			testSuite.numberOfBlockedTests = testSuite.numberOfBlockedTests + 1;
  	  		}
  	  		
  	  		if(tr.time > 10) {
  	  			testSuite.numberOfSlowTests = testSuite.numberOfSlowTests + 1;
  	  		}
  	  	}
  	  
  	  	return testSuite;
		
	}
	
	public static List<TestResult> getTestResults(JSONObject jo) {
		
		JSONArray results = (JSONArray)jo.get("results");
		Iterator itr = results.iterator();
	      
  	  	List<TestResult> testResultsList = new ArrayList<TestResult>();
	      
  	  	while (itr.hasNext()) {
  	  		JSONObject result = (JSONObject)itr.next();
	    	  
  	  		TestResult testResult = getTestResult(result);
  	  		testResultsList.add(testResult);
  	  	}
  	  	
  	  	return testResultsList;
		
	}
	
	public static TestResult getTestResult(JSONObject jo) {
		
		TestResult testResult = new TestResult();
  		testResult.name = ((String)jo.get("test_name")).trim();
	  
  		String timeString = ((String)jo.get("time")).trim();
    	  
  		if(!Strings.isNullOrEmpty(timeString)) {
  			testResult.time = Double.parseDouble(timeString);
  		}
    	  
  		testResult.status = ((String)jo.get("status")).trim();
  		
  		return testResult;
	}
	
}
