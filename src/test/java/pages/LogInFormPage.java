package pages;

import model.ClientPanelForm;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class LogInFormPage extends BasePage {

    public LogInFormPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "clientLogin")
    WebElement inputClientLogin;

    @FindBy(id = "clientPass")
    WebElement inputClientPass;

    @FindBy(id = "__submit_PK_M0118")
    WebElement submitButtonLogIn;

    @FindBy(id = "login")
    WebElement createLogin;

    @FindBy(id = "pass")
    WebElement createUserPassword;

    @FindBy(id = "passRepeat")
    WebElement repeatUserPassword;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "street")
    WebElement street;

    @FindBy(id = "localNo")
    WebElement localNumber;

    @FindBy(id = "postCode")
    WebElement postCode;

    @FindBy(id = "place")
    WebElement city;

    @FindBy(id = "provinceId")
    WebElement provinceName;

    @FindBy(id = "countryCode")
    WebElement countryName;

    @FindBy(id = "client_Email")
    WebElement clientEmail;

    @FindBy(id = "phone")
    WebElement clientPhone;

    @FindBy(id = "acceptRegister")
    WebElement acceptRegister;

    @FindBy(id = "_submit_PK_M0101")
    WebElement buttonCreateNewAccount;

    @FindBy(id = "statusNotFirm")
    WebElement radioButtonNotFirm;

    @FindBy(css = "#PK_M0118 .message-text")
    WebElement redAlertBox;

    @FindBy(className = "recaptcha-checkbox-border") //"#rc-anchor-container .recaptcha-checkbox-border")
    WebElement recaptchaCheckbox;

    @FindBy(id = "login_error")
    WebElement loginErrorInfo;

    @FindBy(id = "pass_error")
    WebElement passErrorInfo;

    @FindBy(id = "passRepeat_error")
    WebElement passRepeatErrorInfo;

    public void clickOnRudioButtonNoFirm() {
        radioButtonNotFirm.click();
    }

    public void clickOnButtonLogIn() {
        submitButtonLogIn.click();
    }

    public void setUserDataToLogIn(ClientPanelForm clientPanel) {
        inputClientLogin.sendKeys(clientPanel.getClientLogin());
        inputClientPass.sendKeys(clientPanel.getClientPassword());
    }

    public void sendIncorrectClientLogin(){
        createLogin.sendKeys("tes");
    }

    public void generateCorrectUserPassword(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        createUserPassword.sendKeys(generatedString);
        repeatUserPassword.sendKeys(generatedString);
        System.out.println("Generated user password: " + generatedString);
    }

    public void addEmailAdressToCreateNewAccount() {

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
        clientEmail.sendKeys("username" + randomInt + "@gmail.com");
        System.out.println("Email adress: " + randomInt);
    }

    public void setUserDataToCreateAccount(ClientPanelForm clientPanel) {
        createLogin.sendKeys(clientPanel.getClientLogin());
        firstName.sendKeys(clientPanel.getClientFirstName());
        lastName.sendKeys(clientPanel.getClientLastName());
        street.sendKeys(clientPanel.getAddressStreet());
        localNumber.sendKeys(clientPanel.getAddressLocalNumber());
        postCode.sendKeys(clientPanel.getPostCode());
        city.sendKeys(clientPanel.getCityName());

        Select selectProvince = new Select(provinceName);
        selectProvince.selectByValue(clientPanel.getProvince().getValue());

        Select selectCountry = new Select(countryName);
        selectCountry.selectByValue(clientPanel.getCountry().getValue());

        clientPhone.sendKeys(clientPanel.getPhoneNumber());
    }

    public void clickCheckboxAcceptRegister(){
        acceptRegister.click();
    }

    public void clickOnButtonCreateNewAccount(){
        buttonCreateNewAccount.click();
    }

    public boolean isRedAlertBoxDisplayed(){
        return isAlertBoxDisplayed(redAlertBox);
    }
    private boolean isAlertBoxDisplayed(WebElement box){
        wait.until(ExpectedConditions.visibilityOf(box));
        boolean isDisplayed = false;

        try {
            isDisplayed = box.isDisplayed();

        }catch(NoSuchElementException e){
        }

        return isDisplayed;
    }

    public void clickOnRecaptchaCheckbox(){
        recaptchaCheckbox.click();
    }

    public boolean isLoginErrorDisplayed(){
        boolean isDisplayed = false;
        try {
            isDisplayed = loginErrorInfo.isDisplayed();
        }catch (NoSuchElementException e){ }

        return isDisplayed;
    }

    public boolean isPassErrorDisplayed(){
        boolean isDisplayed = false;
        try {
            isDisplayed = passErrorInfo.isDisplayed();
        }catch (NoSuchElementException e){ }

        return isDisplayed;
    }

    public void sendExistingLoginToCreateAccount(){
        createLogin.sendKeys("user1");
    }

    public void sendIncorrectPasswordToCreateAnAccount(){
        createUserPassword.sendKeys("aaa");
        repeatUserPassword.sendKeys("aaa");

    }

    public void sendCorrectLoginToCreateAnAccount(){
        createLogin.sendKeys("testowy_login");
    }

    public String passwordTextErrorInfo(){
        return passErrorInfo.getText();
    }

    public String repeatPassTextErrorInfo(){
        return passRepeatErrorInfo.getText();
    }

    public void sendDiffPassAndRepeatPass(){
        createUserPassword.sendKeys("GoodPass1");
        repeatUserPassword.sendKeys("Goodpass1");
    }
    }

