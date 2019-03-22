package TravelOfficeEx;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TOServiceTest {
    TOService toService;
    Address address1;
    Address address2;
    Customer customer1;
    Customer customer2;
    DomesticTrip trip1;
    AbroadTrip trip2;
    TravelOffice travelOffice;

    public TOServiceTest() {
        this.toService = new TOService(new TravelOffice());
    }

    @Before
    public void setUp() throws Exception {

        address1 = new Address("Wolności", "128", "Zbirów", "38-109");
        address2 = new Address("Kopernika", "65", "Wąchock", "58-189");
        customer1 = new Customer("Sasha Baron");
        customer2 = new Customer("Justin Bieber");
        customer1.setAddress(address1);
        customer2.setAddress(address2);
        toService.addCustomer(customer1);
        toService.addCustomer(customer2);
        trip1 = new DomesticTrip(LocalDate.of(2019, 2, 28), LocalDate.of(2019, 3, 21), "Wigry");
        trip2 = new AbroadTrip(LocalDate.of(2019, 3, 22), LocalDate.of(2019, 4, 16), "Sri Lanka");
        trip1.setPrice(2500);
        trip2.setPrice(5500);
        toService.addTrip("wig", trip1);
        toService.addTrip("sri", trip2);
    }

    @Test
    public void shouldshowTrips() {

    }

    @Test
    public void showCustomers() {
    }

    @Test
    public void shuoldIncreaseSizeOfTripMapAfterAdd() {
        //when
        toService.addTrip("bal", new DomesticTrip(LocalDate.of(2019, 3, 6), LocalDate.of(2019, 6, 7), "Bali"));
        int result = toService.getMapOfTrips().size();
        int expected = 3;
        //then
        assertEquals(expected, result);
    }


    @Test
    public void shuoldFindNameOfNewTripInMap() {
        //when
        toService.addTrip("bal", new DomesticTrip(LocalDate.of(2019, 3, 6), LocalDate.of(2019, 6, 7), "Bali"));
        boolean result = toService.getMapOfTrips().values().toString().contains("Bali");
        //then
        assertTrue(result);
    }

    @Test
    public void shouldGiveCustomerByName() throws NoSuchCustomerException {
        //when
        String name = "sas";
        Customer expected = customer1;
        Customer result = toService.findCustomerByName(name);
        assertEquals(expected, result);
    }
    @Test(expected = NoSuchCustomerException.class)
    public void shouldThrowExcepion() throws NoSuchCustomerException {
        String name = "ABC";
    toService.findCustomerByName(name);
    }

    @Test
    public void getMapOfTrips() {
        int result = toService.getMapOfTrips().size();
        int expected = 2;
        assertEquals(expected, result);
    }
    @Test
    public void checkIfSetOfCustomersHasSize2() {
        int result = toService.getSetOfCustomers().size();
        assertEquals(result,2);
    }
    @Test
    public void getSetOfCustomersContainsSasha() {
        boolean result = toService.getSetOfCustomers().toString().contains("Sasha");
        assertTrue(result);
    }

    @Test
    public void removeTripCheckIfMapTripDecrease() throws NoSuchTripException {
        toService.removeTrip("wig");
        int result = toService.getMapOfTrips().size();
        assertEquals(1,result);

    }
    @Test(expected = NoSuchTripException.class)
    public void removeTripCheckIfMapTrip() throws NoSuchTripException {
        toService.removeTrip("abs");

    }
    @Test
    public void shouldCheckIfAddCustomerWithSameNameIncreaseSet() {
        String newName = "Sasha Baron";
        boolean result = toService.customerInSetChecker(newName);
        assertTrue(result);
    }

    @Test
    public void shouldCheckIfAddExistingCustomerNotIncreaseSet() {
        //when
        toService.addCustomer(customer2);
        int result = toService.getSetOfCustomers().size();
        int expected = 2;
        //then
        assertEquals(expected, result);

    }
}