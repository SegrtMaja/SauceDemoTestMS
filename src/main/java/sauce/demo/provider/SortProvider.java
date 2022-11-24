package sauce.demo.provider;

import org.testng.annotations.DataProvider;

public class SortProvider {
    @DataProvider(name = "SortingPriceDataProvider")
    public static Object[][] getDataSortingPriceProvider(){
        return new Object[][] {
                { "Price (low to high)"},
                { "Price (high to low)" },
                {"Name (A to Z)"},
                {"Name (Z to A)"}
        };
    }

}
