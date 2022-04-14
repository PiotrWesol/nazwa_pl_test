package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopMenuPage extends BasePage{

    public TopMenuPage (WebDriver driver){
        super(driver);
    }

    @FindBy(className = "login_pk_link")
    WebElement clientPanel;

    public void clickClientPanel(){
        clientPanel.click();
    }
}
