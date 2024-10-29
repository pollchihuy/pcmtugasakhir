package coid.bcafinance.pcmtugasakhir;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class RunTest {
    public static void main(String[] args) {
        String rootProject = System.getProperty("user.dir");
        TestNG runner = new TestNG();
        List<String> suitefiles = new ArrayList<String>();

        for (int i = 0; i < 10 ; i++) {
            suitefiles.add(".\\run-suite.xml");
            runner.setTestSuites(suitefiles);
        }
        runner.run();
    }
}