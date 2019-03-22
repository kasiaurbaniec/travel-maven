package TravelOfficeEx;

import java.util.Map;
import java.util.Set;

public class TOService {
    public TOService(TravelOffice to) {
        this.to = to;
    }

    TravelOffice to=new TravelOffice();
    public void showTrips() {
        to.showTrips();
    }
    public void showCustomers(){
        to.showCustomers();
    }
    public void addTrip(String id, Trip trip){
        to.addTrip(id,trip);
    }
    public Customer findCustomerByName(String name) throws NoSuchCustomerException {
        return to.findCustomerByName(name);
    }
    public Map<String, Trip> getMapOfTrips(){
        return to.getMapOfTrips();
    }
    public Set<Customer> getSetOfCustomers(){
        return to.getSetOfCustomers();
    }
    public boolean removeTrip(String id) throws NoSuchTripException {
        return to.removeTrip(id);
    }
    public void addCustomer(Customer customer) {
        to.addCustomer(customer);
    }
    public boolean customerInSetChecker(String name){
        return to.customerInSetChecker(name);
    }
}
