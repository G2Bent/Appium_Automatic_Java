package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class ExtentTestNGIReporterListener implements IReporter {

    private static final String OUTPUT_FOLDER = System.getProperty("user.dir");
    private static final String FILE_NAME = "\\src\\test\\java\\report\\extend.html";

    private ExtentReports extent;
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();
        for (ISuite suite : suites) {
            Map result = suite.getResults();
            for (Object obj : result.values()) {
                ISuiteResult r = (ISuiteResult) obj;
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);
            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void init() {
        System.out.println(OUTPUT_FOLDER + FILE_NAME);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
                test.getModel().setDescription(result.getMethod().getDescription());
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


}