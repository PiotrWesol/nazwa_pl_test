package tests;

import enums.Country;
import enums.PolandProvince;
import model.ClientPanelForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LogInFormPage;
import pages.TopMenuPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LogInFormTest extends BaseTest {
    private LogInFormPage logInFormPage;
    private TopMenuPage topMenuPage;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        logInFormPage = new LogInFormPage(driver);
        topMenuPage = new TopMenuPage(driver);
    }

    @Test
    public void shouldNotAllowToLogInWithoutLoginAndPassword() {

        topMenuPage.clickClientPanel();
        logInFormPage.clickOnButtonLogIn();

        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);

        assertThat(alertMessage.startsWith("Podany login jest nieprawidłowy")).isTrue();

    }

    @Test
    public void shouldNotAllowToLogInWithWrongLoginAndPassword() {
        topMenuPage.clickClientPanel();

        ClientPanelForm clientPanelForm = new ClientPanelForm();
        clientPanelForm.setClientLogin("Test_login");
        clientPanelForm.setClientPassword("Test_Pass");
        logInFormPage.setUserDataToLogIn(clientPanelForm);
        logInFormPage.clickOnButtonLogIn();

        assertThat(logInFormPage.isRedAlertBoxDisplayed()).isTrue();

    }

    @Test
    public void shouldNotAllowToLoginWithOnlyLogin() {
        topMenuPage.clickClientPanel();

        ClientPanelForm clientPanelForm = new ClientPanelForm();
        clientPanelForm.setClientLogin("Test_login");
        clientPanelForm.setClientPassword("");
        logInFormPage.setUserDataToLogIn(clientPanelForm);
        logInFormPage.clickOnButtonLogIn();
        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);

        assertThat(alertMessage.startsWith("Nieprawidłowe hasło")).isTrue();
    }

    @Test
    public void shouldNotAllowToLoginWithOnlyPassword() {
        topMenuPage.clickClientPanel();

        ClientPanelForm clientPanelForm = new ClientPanelForm();
        clientPanelForm.setClientLogin("");
        clientPanelForm.setClientPassword("Test_pass");
        logInFormPage.setUserDataToLogIn(clientPanelForm);
        logInFormPage.clickOnButtonLogIn();
        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);

        assertThat(alertMessage.startsWith("Podany login jest nieprawidłowy.")).isTrue();
    }

    @Test
    public void shouldDisplayAlertInfoWhenLoginExists() {
        topMenuPage.clickClientPanel();
        logInFormPage.sendExistingLoginToCreateAccount();
        logInFormPage.generateCorrectUserPassword();

        assertThat(logInFormPage.isLoginErrorDisplayed()).isTrue();

    }

    @Test
    public void shouldDisplayAlertWhenCreatingLoginIsIncorrect() {
        topMenuPage.clickClientPanel();
        logInFormPage.sendIncorrectClientLogin();
        logInFormPage.generateCorrectUserPassword();

        assertThat(logInFormPage.isLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldDisplayAlertWhenPasswordIsIncorrect() {
        topMenuPage.clickClientPanel();
        logInFormPage.sendCorrectLoginToCreateAnAccount();
        logInFormPage.sendIncorrectPasswordToCreateAnAccount();

        assertThat(logInFormPage.isPassErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldDisplayTextWhenPasswordHasNotEnoughChar() {
        topMenuPage.clickClientPanel();
        logInFormPage.sendIncorrectPasswordToCreateAnAccount();

        assertThat(logInFormPage.passwordTextErrorInfo()).isEqualTo("Hasło musi składać się przynajmniej z 8 znaków.");
    }

    @Test
    public void shouldDisplayTextWhenPassAndRepeatPassAreDiff() {
        topMenuPage.clickClientPanel();
        logInFormPage.sendDiffPassAndRepeatPass();
        logInFormPage.sendCorrectLoginToCreateAnAccount();


        assertThat(logInFormPage.repeatPassTextErrorInfo()).isEqualTo("Pola \"Hasło\" i \"Powtórzenie hasła\" muszą mieć taką samą zawartość.");

    }

    @Test
    public void shouldCreateAnAccountWithCorrectData() {
        topMenuPage.clickClientPanel();
        logInFormPage.clickOnRudioButtonNoFirm();
        logInFormPage.generateCorrectUserPassword();
        logInFormPage.addEmailAdressToCreateNewAccount();

        ClientPanelForm clientPanelForm = new ClientPanelForm();
        clientPanelForm.setClientLogin("user1");
        clientPanelForm.setClientFirstName("User_name");
        clientPanelForm.setClientLastName("User_lastName");
        clientPanelForm.setAddressStreet("Hofmana Vlastimila 1");
        clientPanelForm.setAddressLocalNumber("5");
        clientPanelForm.setPostCode("30-210");
        clientPanelForm.setCityName("Krakow");
        clientPanelForm.setProvince(PolandProvince.LODZKIE);
        clientPanelForm.setCountry(Country.POLAND);
        clientPanelForm.setPhoneNumber("501236236");

        logInFormPage.setUserDataToCreateAccount(clientPanelForm);
        logInFormPage.clickCheckboxAcceptRegister();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");


        logInFormPage.clickOnRecaptchaCheckbox();
        logInFormPage.clickOnButtonCreateNewAccount();

    }

    @Test
    public void shouldNotCreateAnAccountWithTheSameLogin() {
        topMenuPage.clickClientPanel();
        logInFormPage.clickOnRudioButtonNoFirm();
        logInFormPage.generateCorrectUserPassword();
        logInFormPage.addEmailAdressToCreateNewAccount();

        ClientPanelForm clientPanelForm = new ClientPanelForm();
        clientPanelForm.setClientLogin("user1");
        clientPanelForm.setClientFirstName("User_name");
        clientPanelForm.setClientLastName("User_lastName");
        clientPanelForm.setAddressStreet("Hofmana Vlastimila 1");
        clientPanelForm.setAddressLocalNumber("5");
        clientPanelForm.setPostCode("30-210");
        clientPanelForm.setCityName("Krakow");
        clientPanelForm.setProvince(PolandProvince.LODZKIE);
        clientPanelForm.setCountry(Country.POLAND);
        clientPanelForm.setPhoneNumber("501236236");

        logInFormPage.setUserDataToCreateAccount(clientPanelForm);
        logInFormPage.clickCheckboxAcceptRegister();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");


        logInFormPage.clickOnRecaptchaCheckbox();
        logInFormPage.clickOnButtonCreateNewAccount();

        assertThat(logInFormPage.isLoginErrorDisplayed()).isTrue();
    }

}

