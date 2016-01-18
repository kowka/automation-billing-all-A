package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountHeader {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public MyAccountHeader(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }


    public void openPremiumPlansPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("wix-menu")));

            if (webDriver.findElements(By.cssSelector("div[class*='__ADORIC__1']")).size() != 0 && webDriver.findElement(By.cssSelector("div[class*='__ADORIC__1']")).isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.cssSelector("div.element-shape.closeLightboxButton"))));
                webDriver.findElement(By.cssSelector("div.element-shape.closeLightboxButton")).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*='__ADORIC__1']")));
            }

            Actions mouseAction = new Actions(webDriver);
            mouseAction.moveToElement(webDriver.findElement(By.id("wm-subscriptions"))).perform();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wm-sub-upgrade-plans")));
            webDriver.findElement(By.id("wm-sub-upgrade-plans")).click();
            Thread.sleep(2_000);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to open Premium Plans page", ex);
        }
    }

    public void waitForMyPremiumPlansPageLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".plans-container.ng-scope")));
        } catch (Exception ex) {
            throw new RuntimeException("Page was not loaded", ex);
        }
    }
}
