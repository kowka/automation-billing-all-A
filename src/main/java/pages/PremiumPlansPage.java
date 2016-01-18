package pages;

import com.wixpress.hoopoe.url.Url;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.elasticsearch.common.mvel2.util.PropertyTools.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PremiumPlansPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    private String newAssignedSite;
    private String newBillingUrl;
    private String newBillingPage;

    public PremiumPlansPage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public void waitForMyPremiumPlanPageLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".plan-console-header.ng-binding")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".plan-info-container.ng-scope")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("item-box")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("plan-info-item-header")));
        } catch (Exception ex) {
            throw new RuntimeException("Page with one premium package was not loaded", ex);
        }
    }

    public void waitForMyPremiumPlansPageLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".plan-console-header.ng-binding")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".plan-info-container.ng-scope")));
        } catch (Exception ex) {
            throw new RuntimeException("Page was not loaded", ex);
        }
    }

//    public void chooseEcommercePlan() {
//
//        WebElement eComPackage;
//
//        try {
//            List<WebElement> packages = webDriver.findElements(By.cssSelector("span[class='.info-item-name-header.ng-binding']"));
//            for (WebElement packages: packages) {
//                if (package.contains("eCommerce")) {
//                    eComPackage =package;
//                }
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException("eCommerce Plan wasn't found", ex);
//        }
//    }


    public void openPlanDetails() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item-box.plan-info-hover-trans")));
            sleep(2_000);
            webDriver.findElement(By.cssSelector(".item-box.plan-info-hover-trans")).click();

            sleep(2_000);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("plan-info-details"))));
        } catch (Exception ex) {
            throw new RuntimeException("Plan details aren't opened", ex);
        }
    }

    public void closePlanDetails() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item-box.plan-info-hover-trans")));
            webDriver.findElement(By.cssSelector(".item-box.plan-info-hover-trans")).click();

            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("plan-info-details"))));


            webDriver.findElement(By.cssSelector(".plan-info-item-header.enable-pointer")).click();
            sleep(2_000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item-box.plan-info-hover-trans")));
        } catch (Exception ex) {
            throw new RuntimeException("Plan details aren't closed", ex);
        }
    }

    public void changeSiteOfPlan() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[ng-click=\"planConsoleController.onTransferClick(userPlan)\"]")));
            webDriver.findElement(By.cssSelector("span[ng-click=\"planConsoleController.onTransferClick(userPlan)\"]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));

            newAssignedSite = webDriver.findElement(By.cssSelector(".site-name-text.ng-binding")).getText();
            sleep(2_000);
            webDriver.findElement(By.cssSelector(".continue-blue-button.ng-binding")).click();
        } catch (Exception ex) {
            throw new RuntimeException("Assigned site wasn't changed", ex);
        }
    }

    public void unassignPlanModal() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[ng-click=\"planConsoleController.openUnAssignModal(userPlan)\"]")));
            webDriver.findElement(By.cssSelector("span[ng-click=\"planConsoleController.openUnAssignModal(userPlan)\"]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));

            sleep(2_000);
            webDriver.findElement(By.cssSelector("button[ng-click=\"unAssignSiteModalManagerController.closeModal()\"]")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));

            sleep(2_000);
            webDriver.findElement(By.cssSelector("span[ng-click=\"planConsoleController.openUnAssignModal(userPlan)\"]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));

            sleep(2_000);
            webDriver.findElement(By.cssSelector("button[ng-click=\"unAssignSiteModalManagerController.unAssign()\"]")).click();
            sleep(2_000);

        } catch (Exception ex) {
            throw new RuntimeException("Plan wasn't unassigned", ex);
        }
    }

    public void planDetails() {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("plan-labels-container")));
            sleep(2_000);


            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("plan-values-container")));
            sleep(2_000);


        } catch (Exception ex) {
            throw new RuntimeException("Plan details look bad", ex);
        }
    }


    public void assignPlanToSite() {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[ng-click=\"planConsoleController.onAssignClick(userPlan)\"]")));
            sleep(2_000);
            webDriver.findElement(By.cssSelector("span[ng-click=\"planConsoleController.onAssignClick(userPlan)\"]")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));
            sleep(2_000);
            webDriver.findElement(By.cssSelector(".continue-blue-button.ng-binding")).click();


        } catch (Exception ex) {
            throw new RuntimeException("Plan wasn't assign to site", ex);
        }
    }


    public void clickManagePremiumButton() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("actions-container"))).isDisplayed();
            sleep(2_000);

            webDriver.findElement(By.cssSelector("button[premium-transparent-button.manage-payments-btn.ng-binding]")).click();
            sleep(2_000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("package_table_view_details_div")));
        } catch (Exception ex) {
            throw new RuntimeException("Manage Premium button isn't work");
        }
    }

    public void freeSitesArea() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".your-free-sites.ng-binding"))).isDisplayed();
            sleep(2_000);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("item-box"))).isDisplayed();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[ng-click=\"planConsoleController.onFreeSiteUpgradeClick(freeSite.siteId)\"]"))).isDisplayed();
            sleep(2_000);
            } catch (Exception ex) {
                throw new RuntimeException("Free sites area isn't shown");
            }
        }


// ---------------------- CHECKERS ----------------------- //

    public void checkMyPremiumPlanPage() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button[class*='manage-payments-btn']")));
        assertTrue(webDriver.findElement(By.className("plan-info-item-header")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("plan-info-details")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("plan-labels-container")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("plan-values-container")).isDisplayed());
        assertTrue(webDriver.findElement(By.cssSelector(".premium-transparent-button.manage-payments-btn.ng-binding")).isDisplayed());

        webDriver.switchTo().defaultContent();
    }

    public void checkMyPremiumPlansPage() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".plan-console-header.ng-binding")));
        assertTrue(webDriver.findElement(By.cssSelector(".item-box.plan-info-hover-trans")).isDisplayed());
        assertTrue(webDriver.findElement(By.cssSelector(".plan-info-item-header.enable-pointer")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("site-info-item-header")).isDisplayed());

        webDriver.switchTo().defaultContent();
    }

    public void checkChangeLinkForSite() {
        assertTrue(webDriver.findElement(By.className("plan-value")).getText().contains(newAssignedSite));
    }

    public void checkPlanIsUnassigned() {

        assertTrue(webDriver.findElement(By.cssSelector("span[ng-click=\"planConsoleController.onAssignClick(userPlan)\"]")).isDisplayed());

    }

    public void checkVioletString() throws InterruptedException {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".site-thumb-ribbon.ng-scope")));
        assertTrue(webDriver.findElement(By.cssSelector(".site-thumb-ribbon.ng-scope")).isDisplayed());
    }

    public void checkPlanDetails() {

        assertTrue(webDriver.findElement(By.className("plan-labels-container")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("plan-values-container")).isDisplayed());

    }

    public void checkPlanIsAssigned() {

        assertTrue(webDriver.findElement(By.cssSelector("span[ng-if=\"planConsoleController.isOtherSiteExist(userPlan)\"]")).isDisplayed());
    }

    public void checkManagePremiumButton() {

        newBillingPage = webDriver.findElement(By.className("manage-premium-title")).getText();
        assertTrue(webDriver.findElement(By.className("manage-premium-title")).getText().contains(newBillingPage));

//        newBillingUrl = webDriver.getCurrentUrl();
//        assertTrue(newBillingUrl.contains("SBSPaymentConsole"));

    }

    public void checkUpgradeButton() throws InterruptedException {


        assertTrue(webDriver.findElement(By.cssSelector("button[class=\"upgrade-free-site-button premium-transparent-purple-button ng-binding\"]")).isDisplayed());
        webDriver.findElement(By.cssSelector("button[class=\"upgrade-free-site-button premium-transparent-purple-button ng-binding\"]")).click();
        Thread.sleep(2_000);
    }


}


