package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({"system:properties",
        "classpath:${webdriver}.driver.properties",
        "classpath:local.driver.properties"})
public interface WebDriverConfig extends Config {

    @Key("driver.remote")
    boolean isRemote();

    @Key("driver.url")
    String getURL();

    @Key("driver.browser")
    String getBrowser();

    @Key("driver.browser.version")
    String getBrowserVersion();

}
