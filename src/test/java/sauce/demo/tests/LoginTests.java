package sauce.demo.tests;

import sauce.demo.models.User;
import sauce.demo.pages.LoginPage;
import sauce.demo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginWithStandardUser() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        /*loginPage.setUserName("standard_userr");
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();*/
        User user = new User("standard_user", "secret_sauce");
        loginPage.login(user);

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDisplayed(), true);


        productsPage.closePage();
    }

    @Test
    public void verifyLoginWithLockedUser() {

    }

}
