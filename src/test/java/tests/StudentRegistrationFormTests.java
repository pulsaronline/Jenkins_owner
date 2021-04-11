package tests;

        import com.codeborne.selenide.Configuration;
        import io.qameta.allure.selenide.AllureSelenide;
        import org.junit.jupiter.api.*;
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
//      gradle clean test -Dweb.browser = safari
        Configuration.browser = System.getProperty("web.browser", "chrome");
        //Configuration.remote = System.getProperty("remote");
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        //gradle clean test -Dremote.web.driver="https://user1:1234@selenoid.autotests.cloud/wd/hub/"
        String remoteWebDriver = System.getProperty("remote.web.driver");
        if(remoteWebDriver !="")
            Configuration.remote = remoteWebDriver;

    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console error logs", getConsoleLogs());
//gradle clean test -Dremote.web.driver="https://user1:1234@selenoid.autotests.cloud/wd/hub/"
// -Dvideo.storage="https://selenoid.autotests.cloud/video/"
        if(System.getProperty("video.storage") !="")
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
