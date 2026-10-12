package zags.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {
    private static Logger log = LogManager.getLogger(TestListener.class);
    @Override
    public void onTestStart(ITestResult result) {
        log.info(">>> Старт теста: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("!!! Тест упал: {} — {}",
                result.getMethod().getMethodName(),
                result.getThrowable().getMessage());
    }
}
