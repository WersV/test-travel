package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest{

    @Test
    public void searchHotel() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("10/09/2024","30/09/2024");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(2,2);
        test.log(Status.PASS, "Setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));
        List<String> hotelNames = hotelSearchPage.findHotelNames();
        test.log(Status.PASS, "Screenshot", SeleniumHelper.getScreenshot(driver));

        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");
        test.log(Status.PASS, "Assertions passed");

//        hotelSearchPage.SearchWithNoLocation("22/08/2024", "30/08/2024", 2,4);
    }

    @Test(dataProvider = "data")
    public void searchHotelWithDataProvider(String city, String hotel) {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDates("10/09/2024","30/09/2024");
        hotelSearchPage.setTravellers(2,2);
        hotelSearchPage.performSearch();
        List<String> hotelNames = hotelSearchPage.findHotelNames();

        Assert.assertEquals(hotelNames.get(0),hotel);

//        hotelSearchPage.SearchWithNoLocation("22/08/2024", "30/08/2024", 2,4);
    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("excel.xlsx");
    }
}