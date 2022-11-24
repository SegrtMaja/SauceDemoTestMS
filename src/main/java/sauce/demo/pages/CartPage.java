package sauce.demo.pages;

import sauce.demo.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends sauce.demo.pages.BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {

    }


    public WebElement getCartList() {
        return this.driver.findElement(By.xpath("//div[@class='cart_list']"));
    }

    public List<Product> getListProducts() {
        List<Product> toReturn = new ArrayList<>();

        WebElement webElemCartList = this.getCartList();
        List<WebElement> webElementListCartItem = webElemCartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < webElementListCartItem.size(); i++) {
            WebElement webElemInventoryItemName = webElementListCartItem.get(i).findElement(By.xpath(".//div[@class='Nokia lumia 1520']"));
            String inventoryItemName = webElemInventoryItemName.getText();

            WebElement webElemInventoryItemPrice = webElementListCartItem.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String inventoryItemPrice = webElemInventoryItemPrice.getText();

            Product product = new Product(inventoryItemName, Double.parseDouble(inventoryItemPrice.substring(1)));
            toReturn.add(product);
        }
        return toReturn;
    }

    public void continueShopping() {
        this.driver.findElement(By.xpath("//button[@data-test='continue-shopping']")).click();
    }


}
