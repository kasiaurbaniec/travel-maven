package TravelOfficeEx;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Test {
    public static void main(String[] args) throws IOException {
        MainHandler mh = new MainHandler();
        Logger root = Logger.getLogger("");
       Arrays.asList(root.getHandlers()).forEach(x->root.removeHandler(x));
        Logger logger = Logger.getLogger("TravelOffice");
        logger.setUseParentHandlers(false);
        FileHandler fh = new FileHandler("log.txt");
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.info("run!");
        mh.run();


//
//
//        Customer cust1 = new Customer("Kasia");
//        Customer cust2 = new Customer("Ela");
//        Customer cust3 = new Customer("Stasiek");
//        Customer cust4 = new Customer("Heniek");
//        Address addres1 = new Address("Wolności", "178", "Rędziny", "42-242");
//        cust1.setAddress(addres1);
//        cust2.setAddress(addres1);
//        cust3.setAddress(addres1);
//        cust4.setAddress(addres1);
//
//        Date start = new Date(2015, 7, 23);
//        Date end = new Date(2017, 7, 11);
//        Trip trip = new AbroadTrip(start, end, "round The world");
//        DomesticTrip dtrip = new DomesticTrip(new Date(2019, 12, 7), new Date(2019, 12, 12), "Łazy");
//        AbroadTrip abroadTrip = new AbroadTrip(start, end, "Afryka dzika");
//        cust1.assignTrip(trip);
//        trip.setPrice(2300);
//        dtrip.setPrice(2200);
//        abroadTrip.setPrice(5000);
//        TravelOffice travelOffice = new TravelOffice();
//
//        cust1.assignTrip(dtrip);
//        cust2.assignTrip(abroadTrip);
//        cust3.assignTrip(trip);
//        cust4.assignTrip(dtrip);
//        travelOffice.addCustomer(cust1);
//        travelOffice.addCustomer(cust2);
//        travelOffice.addCustomer(cust3);
//        travelOffice.addCustomer(cust4);
//        travelOffice.addTrip("Lazy", dtrip);
//        travelOffice.addTrip("World", trip);
//        travelOffice.addTrip("Afryka", abroadTrip);
//        System.out.println(travelOffice.toString());
//        System.out.println(travelOffice.getCustomerCount());
//        travelOffice.findCustomerByName("Heniek");
//        travelOffice.removeCustomer(cust4);
//        System.out.println(travelOffice.getCustomerCount());
//        travelOffice.removeTrip("Lazy");
//        travelOffice.showTrips();
//        travelOffice.showCustomers();

    }
}
