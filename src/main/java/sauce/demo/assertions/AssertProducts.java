package sauce.demo.assertions;

import sauce.demo.models.Product;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AssertProducts {

    public void assertProduct(Product actual, Product expected) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actual.getName(), expected.getName(), "Name is not equal");
        softAssert.assertEquals(actual.getPrice(), expected.getPrice(), "Price is not equal");
        softAssert.assertEquals(actual.getSrc(), expected.getSrc(), "Source is not equal");

        softAssert.assertAll();

    }

    public void assertHardProduct(Product actual, Product expected) {

        Assert.assertEquals(actual.getName(), expected.getName(), "Name is not equal");
        Assert.assertEquals(actual.getPrice(), expected.getPrice(), "Price is not equal");
        Assert.assertEquals(actual.getSrc(), expected.getSrc(), "Source is not equal");

    }

    public void assertListOfProducts(List<Product> actual, List<Product> expected) {
        Assert.assertEquals(actual.size(), expected.size(), "List size is not as expected");

        boolean isListContainsElement = true;
        for(int i = 0; i < actual.size(); i++) {
            boolean isElementExist = false;
            for(int j = 0; j < expected.size(); j++) {
                if(actual.get(i).getName().equals(expected.get(j).getName())) {
                    isElementExist = true;
                    break;
                }

            }
            if(!isElementExist) {
                isListContainsElement = false;
                break;
            }
        }

        Assert.assertEquals(isListContainsElement, true, "List is not equals");
    }

    public void assertListProductSortByPriceFromLowToHigh(List<Product> actual) {

        boolean isPriceSortFromLowToHigh = true;
        Product firstProduct = actual.get(0);
        for(int i = 1; i < actual.size(); i++) {
            if(firstProduct.getPrice() > actual.get(i).getPrice()) {
                isPriceSortFromLowToHigh = false;
                break;
            }
        }
        Assert.assertEquals(isPriceSortFromLowToHigh, true, "Products list is not sorted form low to high by price");

    }

    public void assertListProductSortByPriceFromHighToLow(List<Product> actual) {

        boolean isPriceSortFromLowToHigh = true;
        Product firstProduct = actual.get(0);
        for(int i = 1; i < actual.size(); i++) {
            if(firstProduct.getPrice() < actual.get(i).getPrice()) {
                isPriceSortFromLowToHigh = false;
                break;
            }
        }
        Assert.assertEquals(isPriceSortFromLowToHigh, true, "Products list is not sorted form high to low by price");

    }

    public void assertListProductSortByPrice(List<Product> actual, String typeOfSorting) {

        boolean isProductSort = true;
        Product firstProduct = actual.get(0);
        for(int i = 1; i < actual.size(); i++) {
            if(typeOfSorting.equals("Price (high to low)")) {
                if (firstProduct.getPrice() < actual.get(i).getPrice()) {
                    isProductSort = false;
                    break;
                }
            }
            else if (typeOfSorting.equals("Price (low to high)")) {
                if (firstProduct.getPrice() > actual.get(i).getPrice()) {
                    isProductSort = false;
                    break;
                }
            }
            else if (typeOfSorting.equals("Name (A to Z)")) {
                if (firstProduct.getName().compareTo(actual.get(i).getName()) > 0) {
                    isProductSort = false;
                    break;
                }
            }
            else if (typeOfSorting.equals("Name (Z to A)")) {
                if (firstProduct.getName().compareTo(actual.get(i).getName()) < 0) {
                    isProductSort = false;
                    break;
                }
            }
            firstProduct = actual.get(i);
        }
        Assert.assertEquals(isProductSort, true, "Products list is not sorted by price from " + typeOfSorting);

    }

    public void assertProducts(Product actualProduct, Product expectedProduct) {
        Assert.assertEquals(actualProduct.getName(), expectedProduct.getName(), "Name is not equal");
        Assert.assertEquals(actualProduct.getPrice(), expectedProduct.getPrice(), "Price is not equal");
        Assert.assertEquals(actualProduct.getSrc(), expectedProduct.getSrc(), "Source is not equal");
    }

    public void softAssertProducts(Product actualProduct, Product expectedProduct) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProduct.getName(), expectedProduct.getName(), "Name is not equal");
        softAssert.assertEquals(actualProduct.getPrice(), expectedProduct.getPrice(), "Price is not equal");
        softAssert.assertEquals(actualProduct.getSrc(), expectedProduct.getSrc(), "Source is not equal");
        softAssert.assertAll();
    }


}
