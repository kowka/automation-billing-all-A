package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PackagePickerPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public PackagePickerPage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.titleIs("Pricing Information, Coupons and Premium Upgrades | WIX"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SITE_CONTAINER")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SITE_ROOT")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("PAGES_CONTAINER")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        } catch (Exception ex) {
            throw new RuntimeException("Page was not loaded", ex);
        }
    }


    // ---------------------- CHECKERS ----------------------- //

    public void checkPackagePickerPage() {
        webDriver.switchTo().frame(webDriver.findElement(By.tagName("iframe")));

        Assert.assertTrue(webDriver.findElement(By.cssSelector("span[key='UnlimitedProductName']")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.cssSelector("span[key='eCommerceProductName']")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.cssSelector("span[key='ComboProductName']")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.cssSelector("span[key='ConnectDomainProductName']")).isDisplayed());

        webDriver.switchTo().defaultContent();
    }


    public void checkPackagePickerForManageButton() {
        webDriver.switchTo().frame(webDriver.findElement(By.tagName("iframe")));

        Assert.assertTrue(webDriver.findElement(By.id("package_table_view_details_div")).isDisplayed());
        webDriver.switchTo().defaultContent();
    }

    public void checkPackagePickerForUpgradeButton() {
        webDriver.switchTo().frame(webDriver.findElement(By.tagName("iframe")));

        Assert.assertTrue(webDriver.findElement(By.className("im-top-class")).isDisplayed());
        webDriver.switchTo().defaultContent();
    }
}


