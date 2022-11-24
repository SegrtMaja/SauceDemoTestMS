package sauce.demo.pages;

import sauce.demo.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends sauce.demo.pages.BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        this.driver.get("https://www.saucedemo.com/inventory.html");
        this.driver.manage().window().maximize();
    }

    public List<WebElement> getInventoryItems() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(6000));
        wait.until(
                ExpectedConditions.numberOfElementsToBe(By.className("inventory_list"), 1));

        WebElement inventoryList = this.driver.findElement(By.xpath("//div[@class='inventory_list']"));
        return inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));
    }

    public boolean isDisplayed() {
        boolean toReturn = false;
        if(this.driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            toReturn = true;
        }
        return toReturn;
    }

    public boolean isDisplayedByWebElement() {
        boolean toReturn = false;
        List<WebElement> inventoryContainerList = this.driver.findElements(By.xpath("//div[@id='inventory_container']"));
        if(inventoryContainerList.size() == 2) {
            toReturn = true;
        }
        return toReturn;
    }

    public void addItemToCartByName(String name) {
        List<WebElement> webElementList = this.getInventoryItems();

        int index = 0;
        for(int i = 0; i < webElementList.size(); i++) {
            WebElement webElementItemName = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemName = webElementItemName.getText();

            if(itemName.equals(name)) {
                index = i;
                break;
            }
        }
        WebElement btnAddToCart = webElementList.get(index).findElement(By.xpath(".//button"));
        btnAddToCart.click();
    }

    public void removeItem(String name) {
        List<WebElement> webElementList = this.getInventoryItems();

        int index = 0;
        for(int i = 0; i < webElementList.size(); i++) {
            WebElement webElementItemName = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemName = webElementItemName.getText();

            if(itemName.equals(name)) {
                index = i;
                break;
            }
        }

        WebElement btnRemove = webElementList.get(index).findElement(By.xpath(".//button"));
        btnRemove.click();
    }


    public void addItemToCartByName(List<String> listItemName) {
        List<WebElement> webElementList = this.getInventoryItems();

        for(int j = 0; j < listItemName.size(); j++) {

            int index = 0;
            for (int i = 0; i < webElementList.size(); i++) {
                WebElement webElementItemName = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
                String itemName = webElementItemName.getText();

                if (itemName.equals(listItemName.get(j))) {
                    index = i;
                    break;
                }
            }
            WebElement btnAddToCart = webElementList.get(index).findElement(By.xpath(".//button"));
            btnAddToCart.click();
        }
    }

    public void addAllItemsToCart() {
        List<WebElement> webElementList = this.getInventoryItems();

        for(int i = 0; i < webElementList.size(); i++) {
            WebElement btnAddToCart = webElementList.get(i).findElement(By.xpath(".//button"));
            btnAddToCart.click();
        }
    }

    public int cartItemNo(){
        WebElement cartLink = this.driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        List<WebElement> spanList = cartLink.findElements(By.xpath(".//span"));
        int noOfItems;
        if (spanList.size()!=0){
            noOfItems = Integer.parseInt(spanList.get(0).getText());
        }
        else {
            noOfItems=0;
        }
        return noOfItems;
    }

    public List<WebElement> getListInventoryItems() {
        WebElement inventoryList = this.driver.findElement(By.xpath("//div[@class='inventory_list']"));

        return inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));
    }

    public List<Product> getListProducts() {
        List<Product> toReturn = new ArrayList<>();
        List<WebElement> webElementList = new ArrayList<>();
        webElementList = this.getListInventoryItems();

        for(int i = 0; i < webElementList.size(); i++) {

            WebElement item = webElementList.get(i);

            WebElement itemName = item.findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String name = itemName.getText();

            WebElement itemPrice = item.findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String price = itemPrice.getText();
            String tmpPrice = price.substring(1);

            WebElement itemImage = item.findElement(By.xpath(".//img[@class='inventory_item_img']"));
            String src = itemImage.getAttribute("src");

            Product product = new Product(name, Double.parseDouble(tmpPrice), src);
            toReturn.add(product);
        }
        return toReturn;
    }

    private WebElement getShoppingCart() {
        return this.driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public void clickOnCart() {
        this.getShoppingCart().click();
    }

    public WebElement getCheapestProduct() {

        List<WebElement> webElementList = this.getListInventoryItems();

        int index = 0;
        WebElement firstItem = webElementList.get(0).findElement(By.xpath(".//div[@class='inventory_item_price']"));
        double cheapestPrice = Double.parseDouble(firstItem.getText().substring(1));

        for(int i = 1; i < webElementList.size(); i++ ) {
            WebElement webElementPrice = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String price = webElementPrice.getText();
            Double dblPrice = Double.parseDouble(price.substring(1));

            //if(cheapestPrice > dblPrice) {
            if(cheapestPrice > dblPrice) {
                cheapestPrice = dblPrice;
                index = i;
            }
        }
        return webElementList.get(index);
    }

    public void selectCheapestItem() {
        WebElement webElement = this.getCheapestProduct();
        webElement.findElement(By.xpath(".//button")).click();
    }

    public void sortBy(String sortValue) {
        new Select(driver.findElement(By.cssSelector("select[class='product_sort_container']"))).selectByVisibleText(sortValue);
    }

    public WebElement getProductSortContainer() {
        return this.driver.findElement(By.xpath("//select[@class='product_sort_container']"));
    }

    public void selectSortBy(String sortItem) {
        WebElement productSortContainer = this.getProductSortContainer();
        List<WebElement> options = productSortContainer.findElements(By.xpath(".//option"));

        productSortContainer.click();
        for(int i = 0; i < options.size(); i++) {
            if(options.get(i).getText().equals(sortItem)) {
                options.get(i).click();
                break;
            }
        }
    }




    public void closePage() {
        this.driver.close();
        this.driver.quit();
    }

}
