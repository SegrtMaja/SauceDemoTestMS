package sauce.demo.pages;

import org.openqa.selenium.WebDriver;

import java.nio.file.WatchEvent;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void openPage();

}
