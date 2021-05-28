package tests;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helper.AttachmentHelper.*;

public class TestBase {

    static config.DriverConfig DriverConfig = ConfigFactory.create(DriverConfig.class, System.getProperties());

    @BeforeAll
    public static void setup() {
        Configuration.startMaximized = true;
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = DriverConfig.getBrowser();
        Configuration.browserVersion = DriverConfig.getBrowserVersion();

        if (DriverConfig.isRemote()) {
            Configuration.remote = DriverConfig.getURL();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if (System.getProperty("video.storage") != null)
            attachVideo();
        closeWebDriver();
    }
}