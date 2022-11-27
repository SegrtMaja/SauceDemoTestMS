package sauce.demo.tests;

import sauce.demo.assertions.AssertProducts;
import sauce.demo.models.Product;
import sauce.demo.models.User;
import sauce.demo.pages.CartPage;
import sauce.demo.pages.LoginPage;
import sauce.demo.pages.ProductsPage;
import sauce.demo.provider.ProductsProvider;
import sauce.demo.provider.SortProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTests extends BaseTest {

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyAddToCart(String nameOfProduct) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName(nameOfProduct);
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");

    }

    @Test()
    public void verifyAddProductToCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName("");
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");

    }

    @Test(dataProvider = "ProductsNameProviderOnCartPage", dataProviderClass = ProductsProvider.class)
    public void verifyAddProductToCartOnCartPage(String productName) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickOnCart();
        CartPage cartPage = new CartPage(driver);
        List<Product> productListExpected = cartPage.getListProducts();
        cartPage.continueShopping();

        productsPage.addItemToCartByName(productName);
        productsPage.clickOnCart();
        List<Product> productListActual = cartPage.getListProducts();

        productListExpected.add(new Product(productName, 23.2));

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListOfProducts(productListActual, productListExpected);

    }

    @Test
    public void verifyAddCheapestProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.sortBy("Price (low to high)");
        productsPage.selectSortBy("Price (low to high)");
        productsPage.selectSortBy("Price (high to low)");
        productsPage.selectSortBy("Name (A to Z)");
        productsPage.selectSortBy("Name (Z to A)");
        System.out.println("test");
    }

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveItem(String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int cartItemNumber = productsPage.cartItemNo();
        productsPage.addItemToCartByName(productName);
        productsPage.removeItem(productName);

        Assert.assertEquals(productsPage.cartItemNo(), cartItemNumber, "Cart number is not as expected");
    }

    @Test(dataProvider = "ProductsListNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveItems(String productFirstName, String productSecondName, String productThirdName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int cartItemNumber = productsPage.cartItemNo();
        productsPage.addItemToCartByName(productFirstName);
        productsPage.addItemToCartByName(productSecondName);
        productsPage.addItemToCartByName(productThirdName);

        productsPage.removeItem(productFirstName);
        Assert.assertEquals(productsPage.cartItemNo(), cartItemNumber + 2, "Cart number is not as expected");
    }

    @Test
    public void verifySortProductByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectSortBy("Price (high to low)");

        List<Product> productListActual = productsPage.getListProducts();

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListProductSortByPriceFromHighToLow(productListActual);

    }

    @Test
    public void verifyProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        List<Product> productListActual = productsPage.getListProducts();

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.softAssertProducts(productListActual.get(0), productListActual.get(1));
    }

    @Test(dataProvider = "SortingPriceDataProvider", dataProviderClass = SortProvider.class)
    public void verifySortProductBy(String typeOfSorting) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectSortBy(typeOfSorting);

        List<Product> productListActual = productsPage.getListProducts();

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListProductSortByPrice(productListActual, typeOfSorting);

    }

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveProduct(String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(productName);
        int productInCartExpected = productsPage.cartItemNo(); //3
        productsPage.removeItem(productName); //-1
        int productInCartActual = productsPage.cartItemNo(); //2

        Assert.assertEquals(productInCartActual, productInCartExpected - 1, "Product number in cart is not as expected");
    }

    @Test(dataProvider = "ProductsNameProviderList", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveProductList(String productNameFirst, String productNameSecond, String productNameThird) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(productNameFirst);
        productsPage.addItemToCartByName(productNameSecond);
        productsPage.addItemToCartByName(productNameThird);
        int productInCartExpected = productsPage.cartItemNo(); //3
        productsPage.removeItem(productNameSecond);
        productsPage.removeItem(productNameThird);//-1
        int productInCartActual = productsPage.cartItemNo(); //2

        Assert.assertEquals(productInCartActual, productInCartExpected - 2, "Product number in cart is not as expected");
    }


    @Test
    public void verifyAssertProduct() {
        Product productJaffa = new Product("Jaffa", 150.0, "Keks");
        Product productPlazma = new Product("Plazme", 112.0, "Keks");

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertHardProduct(productJaffa, productPlazma);
    }

}
