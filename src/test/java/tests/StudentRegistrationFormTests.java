package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.StudentRegistrationFormPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class StudentRegistrationFormTests {
    StudentRegistrationFormPage formPage;

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "1024x768";
        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = System.getProperty("remote");
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }

   /* @BeforeEach
    void beforeEach(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = System.getProperty("remote_driver");
    }*/

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console error logs", getConsoleLogs());
//        if (System.getProperty("video_storage") != null)
        attachVideo();
        closeWebDriver();
    }

    @Test
    @DisplayName("Positive test")
    void okTest() {
       formPage = new StudentRegistrationFormPage();
       formPage.fillThePage("");
          }

    @Test
    @DisplayName("Negative test")
    void brokenTest() {
        formPage = new StudentRegistrationFormPage();
        formPage.fillThePage("ERROR");
    }
}
