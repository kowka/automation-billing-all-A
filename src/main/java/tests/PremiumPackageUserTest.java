package tests;

import com.wixpress.automation.commons.data.users.LightUserData;
import com.wixpress.automation.commons.logging.logger.TestLogger;
import com.wixpress.automation.commons.utils.url.UrlUtils;
import com.wixpress.automation.context.TestProperties;
import com.wixpress.automation.context.builders.TestPropertiesBuilders;
import com.wixpress.automation.eyes.WixEyes;
import com.wixpress.automation.tests.base.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MyAccountHeader;
import pages.PackagePickerPage;
import pages.PremiumPlansPage;

import static java.lang.Thread.sleep;

public class PremiumPackageUserTest extends TestBase {
    TestLogger testLogger;
    WebDriver webDriver;

    MyAccountHeader myAccountHeader;
    PremiumPlansPage premiumPlansPage;
    PackagePickerPage packagePickerPage;

    @Override
    protected TestProperties getTestProperties() {
        LightUserData userData = new LightUserData("automation-premiumusermany@wix.com", "test");
        return TestPropertiesBuilders.customUrlWithExistingUser(userData, UrlUtils.getMyAccountUrl()).build();
    }

    @Override
    protected void beforeTest() {
        testLogger = services.logger();
        webDriver = services.webDriver().getWebDriver();

        myAccountHeader = new MyAccountHeader(webDriver);
        premiumPlansPage = new PremiumPlansPage(webDriver);
        packagePickerPage = new PackagePickerPage(webDriver);

        testLogger.log("Open Premium Plans page");
        myAccountHeader.openPremiumPlansPage();
    }


    @Test
    public void testPremiumPackageWithSomePlans() {
        testLogger.log("Wait for Premium Plans page to load");
        premiumPlansPage.waitForMyPremiumPlansPageLoad();

        testLogger.log("Check page with premium packages");
        premiumPlansPage.checkMyPremiumPlansPage();
    }


    @Test
    @WixEyes.WithWixEyes("Premium plan Details")
    public void testDetailedInfoForPlan() {
//        testLogger.log("Check page with premium packages");
//        premiumPlansPage.checkMyPremiumPlansPage();

        testLogger.log("Click on the first package");
        premiumPlansPage.openPlanDetails();
        services.eyes().captureScreenShot("Premium plan details");
    }

    @Test
    public void testDetailedInfoForPlanClosed() {
        testLogger.log("Close plan details");
        premiumPlansPage.closePlanDetails();
    }

    @Test
    public void testChangeSiteOfPlan() {
        testLogger.log("Open details for plan");
        premiumPlansPage.openPlanDetails();

        testLogger.log("Change site for plan");
        premiumPlansPage.changeSiteOfPlan();

        testLogger.log("Verify that site name was changed for plan");
        premiumPlansPage.checkChangeLinkForSite();
    }

    @Test
    public void testUnassignedSiteOfPlan() throws InterruptedException {
        testLogger.log("Open details for plan");
        premiumPlansPage.openPlanDetails();

        testLogger.log("Unassign site from plan");
        premiumPlansPage.unassignPlanModal();

        Thread.sleep(3_000);
        testLogger.log("Verify that plan is Unassigned");
        premiumPlansPage.checkPlanIsUnassigned();
    }

    @Test
    public void testAssignToSitePlan() throws InterruptedException {
        testLogger.log("Open details for plan");
        premiumPlansPage.openPlanDetails();

        testLogger.log("Assign plan to site");
        premiumPlansPage.assignPlanToSite();

        Thread.sleep(5_000);
        testLogger.log("Verify that plan was assigned");
        premiumPlansPage.checkPlanIsAssigned();


    }

//     @Test
//    public void testPlanDetailsAreShown() {
//         testLogger.log("Open details for plan");
//         premiumPlansPage.openPlanDetails();
//
//         testLogger.log("Verify all plan details are shown");
//         premiumPlansPage.planDetails();
//         premiumPlansPage.checkPlanDetails();
//     }

    @Test
    public void testVioletStripForPremiumPlan() throws InterruptedException {
        testLogger.log("Open Premium Plans page");
        myAccountHeader.openPremiumPlansPage();

        testLogger.log("Verify that Premium site has violet strip");
        premiumPlansPage.checkVioletString() ;

    }

// TODO Should add step to switch to another tab and verify into there

//    @Test
//    public void testManagePremiumButton() {
//        testLogger.log("Open Premium Plans page");
//        myAccountHeader.openPremiumPlansPage();
//
////        testLogger.log("Choose eCommerce plan");
////        premiumPlansPage.chooseEcommercePlan();
//
//        testLogger.log("Open plan details");
//        premiumPlansPage.openPlanDetails();
//
//        testLogger.log("Click on Mange Premium button");
//        premiumPlansPage.clickManagePremiumButton();
//
//        testLogger.log("Verify that Billing and Payment page was opened");
//        premiumPlansPage.checkManagePremiumButton();
//        packagePickerPage.checkPackagePickerForManageButton();

//    }


    // TODO Should add step to switch to another tab and verify into there


//    @Test
//    public void testFreeSitesArea() throws InterruptedException {
//        testLogger.log("Open Premium Plans page");
//        myAccountHeader.openPremiumPlansPage();
//
//        testLogger.log("Verify free sites on the page ");
//        premiumPlansPage.freeSitesArea();
//
//        testLogger.log("Verify Upgrade button for free site");
//        premiumPlansPage.checkUpgradeButton();
//
//        // webDriver.switchTo().window("");
//
//        testLogger.log("Verify Upgrade button redirects to Package Picker");
//        packagePickerPage.waitForPageLoad();
//        packagePickerPage.checkPackagePickerForUpgradeButton();
//    }
}