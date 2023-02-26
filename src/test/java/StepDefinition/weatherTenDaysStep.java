package StepDefinition;

import core.common.Utilities;
import core.locators.Locator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

import static core.common.BuiltInAction.*;
import static core.common.Constant.AUT_URL;
import static core.locators.WebDriver.openBrowser;

public class weatherTenDaysStep {

    private Locator tenDayBtn = new Locator(How.XPATH, "//span[.='10 Day']");
    private String expandWeather = "//details[@id='detailIndex{0}']";
    private String dayTemperatureLocation = "(//span[@data-testid=\"TemperatureValue\" and @class=\"DetailsSummary--highTempValue--3PjlX\"])[{0}]";
    private String nightTemperatureLocation = "(//span[@data-testid=\"TemperatureValue\" and @class=\"DetailsSummary--lowTempValue--2tesQ\"])[{0}]";
    private String dayHumidityLocation = "(//div[@class=\"DaypartDetails--DetailsTable--1zK4r DaypartDetails--col1--2TSAK DetailsTable--twoColumn--1p6yA\"]//span[@data-testid=\"PercentageValue\"])[{0}]";
    private String nightHumidityLocation = "(//div[@class=\"DaypartDetails--DetailsTable--1zK4r DaypartDetails--col2--3WBrx DetailsTable--twoColumn--1p6yA\"]//span[@data-testid=\"PercentageValue\"])[{0}]";

    @Given("User go to the website")
    public void user_go_to_the_website() {
        openBrowser("Chrome");
        navigateTo(AUT_URL);
        maximizeWindow();
        System.out.println("User went to the website");
    }
    @When("User go to ten days tab")
    public void user_go_to_ten_days_tab() {
        click(tenDayBtn.getBy());
        System.out.println("User went to 10 Days tab");
    }
    @When("Retrieve ten days")
    public void retrieve_ten_days() {
        List<String[]> data = new ArrayList<String[]>();
        for(int i=1; i<11; i++){
            Locator realExpandWeatherLocation = new Locator(How.XPATH, this.expandWeather.replace("{0}", String.valueOf(1)));
            Locator realDayTemperatureLocation = new Locator(How.XPATH, this.dayTemperatureLocation.replace("{0}", String.valueOf(i)));
            Locator realNightTemperatureLocation = new Locator(How.XPATH, this.nightTemperatureLocation.replace("{0}", String.valueOf(i)));
            Locator realDayHumidityLocation = new Locator(How.XPATH, this.dayHumidityLocation.replace("{0}", String.valueOf(i)));
            Locator realNightHumidityLocation = new Locator(How.XPATH, this.nightHumidityLocation.replace("{0}", String.valueOf(i)));

            data.add(new String[]{getInnerText(realDayTemperatureLocation.getBy()),
                    getInnerText(realNightTemperatureLocation.getBy()),
                    getInnerText(realDayHumidityLocation.getBy()),
                    getInnerText(realNightHumidityLocation.getBy())});
        }
        writteDataAtOne(Utilities.getProjectPath() + "\\weather10DaysFiles.csv", data);

        System.out.println("User writte the weather of 10 days to csv file");
        close();
    }
}
