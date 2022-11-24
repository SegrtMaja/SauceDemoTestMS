package sauce.demo.provider;

import sauce.demo.models.Product;
import org.testng.annotations.DataProvider;

public class ProductsProvider {

    @DataProvider(name = "ProductsNameProvider")
    public static Object[][] getDataProductName(){
        return new Object[][] {
                { "Sauce Labs Backpack"},
                { "Sauce Labs Bike Light" },
                { "Sauce Labs Bolt T-Shirt" },
                {"Sauce Labs Onesie"},
                {"Sauce Labs Fleece Jacket"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @DataProvider(name = "ProductsNameProviderList")
    public static Object[][] getDataProductNameList(){
        return new Object[][] {
                { "Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"},
                { "Sauce Labs Backpack", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie"},

        };
    }

    @DataProvider(name = "ProductsNameProviderOnCartPage")
    public static Object[][] getDataProductNameOnCartPage(){
        return new Object[][] {
                { "Sauce Labs Backpack"},
                { "Sauce Labs Bike Light" }
        };
    }

    @DataProvider(name = "ProductsListNameProvider")
    public static Object[][] getListDataProductNameOnCartPage(){
        return new Object[][] {
                { "Sauce Labs Backpack", "Sauce Labs Bike Light", "Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @DataProvider(name = "ProductsProvider")
    public static Object[][] getDataProduct(){
        return new Object[][] {
                { new Product("Sauce Labs Bike Light", 152.0)}
        };
    }


}
