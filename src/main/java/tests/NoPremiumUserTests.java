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

public class NoPremiumUserTests extends TestBase {
    TestLogger testLogger;
    WebDriver webDriver;

    MyAccountHeader myAccountHeader;
    PremiumPlansPage premiumPlansPage;
    PackagePickerPage packagePickerPage;

    @Override
    protected TestProperties getTestProperties() {
        LightUserData userData = new LightUserData("automation-freeuser@wix.com", "test");
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
    public void testPackagePickerShownForUserWithNoPlans() {
        testLogger.log("Check PackagePicker page is shown");
        packagePickerPage.waitForPageLoad();
        packagePickerPage.checkPackagePickerPage();
    }

}
