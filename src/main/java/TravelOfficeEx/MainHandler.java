package TravelOfficeEx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainHandler implements UserCustomer {
    //    TravelOffice to = new TravelOffice();
    Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger("TravelOffice");
    TOService toService = new TOService(new TravelOffice());

    public void setToService(TOService toService) {
        this.toService = toService;
    }

    public void printMenu() {
        System.out.println();
        System.out.println("1-Dodaj klienta");
        System.out.println("2-Dodaj wycieczkę");
        System.out.println("3-Przypisz wycieczkę do klienta");
        System.out.println("4-Usuń klienta");
        System.out.println("5-Usuń wycieczkę");
        System.out.println("6-Pokaż klientów");
        System.out.println("7-Pokaż wycieczki");
        System.out.println("8-Wyjdz");
    }

    public void run() {
        int option;
        //ładowanie wstępnej bazy
        Address address1 = new Address("Wolności", "128", "Zbirów", "38-109");
        Address address2 = new Address("Kopernika", "65", "Wąchock", "58-189");
        Customer customer1 = new Customer("Sasha Baron");
        Customer customer2 = new Customer("Justin Bieber");
        customer1.setAddress(address1);
        customer2.setAddress(address2);
        toService.addCustomer(customer1);
        toService.addCustomer(customer2);
        DomesticTrip trip1 = new DomesticTrip(LocalDate.of(2019, 2, 28), LocalDate.of(2019, 3, 21), "Wigry");
        AbroadTrip trip2 = new AbroadTrip(LocalDate.of(2019, 3, 22), LocalDate.of(2019, 4, 16), "Sri Lanka");
        trip1.setPrice(2500);
        trip2.setPrice(5500);
        toService.addTrip("wig", trip1);
        toService.addTrip("sri", trip2);

        do {
            printMenu();
            option = scanner.nextInt();
            if (option == 1) {
                addCustomer();
            } else if (option == 2) {
                addTrip();
            } else if (option == 3) {
                assign();
            } else if (option == 4) {
                removeCustomer();
            } else if (option == 5) {
                removeTrip();
            } else if (option == 6) {
                showCustomers();
            } else if (option == 7) {
                showTrip();
            } else if (option == 8) {
                System.out.println("exit");
            } else System.out.println("wrong number");

        } while (option != 8);
    }

    @Override
    public Customer addCustomer() {
        String name;
        boolean checker = false;
        logger.info("start to add new customer");

        name = scanner.next();
        checker = toService.customerInSetChecker(name);

        System.out.println("Give customers Address \n Street:");
        String street = scanner.next();
        System.out.println("Give customers street number");
        String streetNumber = scanner.next();
        System.out.println("Give customers city");
        String city = scanner.next();
        System.out.println("Give customers code");
        String code = scanner.next();
        Address address = new Address(street, streetNumber, city, code);
        logger.info("new address was successfully created");
        Customer customer = new Customer(name);
        customer.setAddress(address);
        logger.info("customer has address");
        toService.addCustomer(customer);
        logger.info("customer was created and added to list");

        return customer;
    }


    @Override
    public Trip addTrip() {
        logger.info("start do create new trip");
        Trip trip = null;
        System.out.println("give id for new trip");
        String name = scanner.next();
        System.out.println("give destination of a trip");
        String desc = scanner.next();
        LocalDate startDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        do {
            System.out.println("give begin date: yyyy-mm-dd");
            String dateStr = scanner.next();
            startDate = LocalDate.parse(dateStr, formatter);
        }
        while (startDate == null);
        logger.fine("create begin date succeeded");
        LocalDate endDate;
        do {
            System.out.println("give date when it end, yyyy-mm-dd");
            String dateStrEnd = scanner.next();
            endDate = LocalDate.parse(dateStrEnd, formatter);
        } while (endDate == null);
        logger.fine("create end date succeeded");
        System.out.println("give price");
        int price = scanner.nextInt();
        System.out.println("type domestic/abroad");
        String domest = scanner.next();
        if (domest.startsWith("d")) {
            System.out.println("own arrival discount");
            int disc = scanner.nextInt();
            trip = new DomesticTrip(startDate, endDate, desc);
            trip.setPrice(price);
            ((DomesticTrip) trip).setOwnArrivalDiscount(disc);
            logger.info("discount included");
        } else {
            trip = new AbroadTrip(startDate, endDate, desc);
            trip.setPrice(price);
            System.out.println("add insurance? y/n ");
            String ins = scanner.next();
            if (ins.startsWith("y")) {
                ((AbroadTrip) trip).setInsurance(true);
                logger.info("insurance included");
            }
        }
        toService.addTrip(name, trip);
        logger.info("new trip created and added to list");
        return trip;
    }

    @Override
    public void assign() {
        Customer customer = null;
        String clientName;
        String tripName;
        Trip trip;
        do {
            System.out.println("give client you want to assign a trip");
            clientName = scanner.next();
            try {
                customer = toService.findCustomerByName(clientName);
                logger.info("customer found");
            } catch (NoSuchCustomerException e) {
                System.out.println(e.getMessage());
            }
        } while (customer == null);
        do {
            System.out.println("give name of trip you want to be assigned");
            tripName = scanner.next();
            trip = toService.getMapOfTrips().get(tripName);
            if (trip == null) {
                System.out.println("no such trip found");
                logger.warning("trip no exist");
            }
            logger.info("trip found");
        } while (trip == null);
        customer.assignTrip(trip);
        logger.info("trip assigned to customer");
    }


    @Override
    public boolean removeCustomer() {
        System.out.println("give name of customer to remove");
        String clientName = scanner.next();
        try {
            Customer cust = toService.findCustomerByName(clientName.toLowerCase());
            toService.getSetOfCustomers().removeIf(x -> x.getName().toLowerCase().startsWith(clientName.toLowerCase()));
            logger.finest("removing customer succeed");
        } catch (NoSuchCustomerException e) {
            System.out.println(e.getMessage());
            logger.warning("removing customer " + clientName + " failed");
        }
        return true;
    }

    @Override
    public boolean removeTrip() {
        if (!toService.getMapOfTrips().isEmpty()) {
            System.out.println("give name of trip to remove");
            String tripName = scanner.next();
            try {
                toService.removeTrip(tripName);
                logger.fine("removing trip " + tripName + " succeed");
            } catch (NoSuchTripException e) {
                System.out.println(e.getMessage());
                logger.warning("removing trip " + tripName + " failed");
            }
            return true;
        } else System.out.println("empty list");
        logger.warning(" attempt to removing from empty list");
        return false;
    }

    @Override
    public void showTrip() {
        toService.showTrips();
        logger.fine("showing trips succeeded");
    }

    @Override
    public void showCustomers() {
        toService.showCustomers();
        logger.finest("showing customers succeeded");
    }
}

