package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;

public class HotelSearchTest extends BaseTest{

    @Test
    public void searchHotel() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Warsaw");
        hotelSearchPage.setDates("22/08/2024","30/08/2024");
        hotelSearchPage.setTravellers(2,2);
        hotelSearchPage.performSearch();
        List<String> hotelNames = hotelSearchPage.findHotelNames();

//        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
//        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
//        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
//        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");

//        hotelSearchPage.SearchWithNoLocation("22/08/2024", "30/08/2024", 2,4);
    }
}