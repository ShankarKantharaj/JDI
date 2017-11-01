package com.epam.jdi.uitests.testing;

import com.epam.jdi.site.epam.EpamSite;
import com.epam.jdi.uitests.core.logger.JDILogger;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static com.epam.jdi.site.epam.EpamSite.homePage;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebBrowsers;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.open;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public abstract class TestsBase extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        init(EpamSite.class);
        open();
        ((JDILogger)WebSettings.logger).setLogLevel(LogLevels.INFO);
        logger.info("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() throws IOException {
        killAllRunWebBrowsers();
    }
}
