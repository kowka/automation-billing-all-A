package tests;

import com.wixpress.automation.commons.data.users.LightUserData;
import com.wixpress.automation.commons.logging.logger.TestLogger;
import com.wixpress.automation.commons.utils.url.UrlUtils;
import com.wixpress.automation.context.TestProperties;
import com.wixpress.automation.context.builders.TestPropertiesBuilders;
import com.wixpress.automation.tests.base.TestBase;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MyAccountHeader;
import pages.PackagePickerPage;
import pages.PremiumPlansPage;

public class OnePremiumPackageUserTests extends TestBase {
    TestLogger testLogger;
    WebDriver webDriver;

    MyAccountHeader myAccountHeader;
    PremiumPlansPage premiumPlansPage;
    PackagePickerPage packagePickerPage;

    @Override
    protected TestProperties getTestProperties() {
        LightUserData userData = new LightUserData("automation-premiumuser@wix.com", "test");
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
    public void testPremiumPlansPageWithOnePackage() {
        testLogger.log("Wait for Premium Plans page to load");
        premiumPlansPage.waitForMyPremiumPlanPageLoad();

        testLogger.log("Check page with one premium package");
        premiumPlansPage.checkMyPremiumPlanPage();
    }

}
