package sauce.demo.tests;

import sauce.demo.models.User;
import sauce.demo.pages.LoginPage;
import sauce.demo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginWithStandardUser() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        User user = new User("standard_user", "secret_sauce");
        loginPage.login(user);

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDisplayed(), true);


        productsPage.closePage();
    }


}
