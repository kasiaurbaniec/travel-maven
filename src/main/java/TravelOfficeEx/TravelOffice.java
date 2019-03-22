package TravelOfficeEx;

import java.util.*;
import java.util.stream.Collectors;

public class TravelOffice {
    private Set<Customer> setOfCustomers = new HashSet<>();
    private Map<String, Trip> mapOfTrips = new HashMap<>();

    public Set<Customer> getSetOfCustomers() {
        return setOfCustomers;
    }

    public Map<String, Trip> getMapOfTrips() {
        return mapOfTrips;
    }

    public void addCustomer(Customer customer) {
        setOfCustomers.add(customer);
//        if (customerCount == 0) {
//            listOfCustomer[0] = customer;
//            customerCount++;
//        } else if
//        (customerCount == 1) {
//            listOfCustomer[1] = customer;
//            customerCount++;
//        } else {
//            Customer[] copiedList = new Customer[getCustomerCount() + 2];
//            System.arraycopy(listOfCustomer, 0, copiedList, 0, listOfCustomer.length);
//            copiedList[listOfCustomer.length] = customer;
//            listOfCustomer = copiedList;
//            customerCount++;
//        }
    }

    public int getCustomerCount() {
        return setOfCustomers.size();
    }

    public String toString() {
        return "Customers: " + setOfCustomers.toString() + "\n" + "Trips: " + mapOfTrips.values().toString();
    }

    public void showTrips() {
        if (mapOfTrips.isEmpty()) {
            System.out.println("List of trips is empty");
        } else
            System.out.println("All trips: ");
        mapOfTrips.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
    }

    public void showCustomers() {
        if (setOfCustomers.isEmpty()) {
            System.out.println("list of customers is empty");
        } else
            System.out.print("Customers");
        setOfCustomers.forEach(x -> System.out.print(x.toString()));
    }

    public void addTrip(String id, Trip trip) {
        mapOfTrips.put(id, trip);
    }

    public boolean removeTrip(String id) throws NoSuchTripException {
        if (mapOfTrips.containsKey(id)) {
            mapOfTrips.remove(id);
            return true;
        }
        throw new NoSuchTripException("no such trip");
    }

    public Customer findCustomerByName(String name) throws NoSuchCustomerException {
        for (Customer c : setOfCustomers) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                return c;
            }
        }
        throw new NoSuchCustomerException("no such customer");
    }

    public boolean removeCustomer(Customer customer) {
        if (setOfCustomers.contains(customer)) {
            setOfCustomers.remove(customer);
            return true;
        }
        return false;
    }
}





