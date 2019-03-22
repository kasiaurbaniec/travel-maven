package TravelOfficeEx;

public class Customer {
private String name;
private Address address;
private Trip trip=null;

    public Customer(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void assignTrip(Trip trip) {
        this.trip = trip;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if(trip==null){
            return "\nname: "+name+" \ntrip: null"+" \naddress: "+address.toString()+"\n";
        }else{
                    return "\nname: "+name+" \ntrip: "+trip.toString()+" \naddress: "+address.toString()+"\n";}}



}
