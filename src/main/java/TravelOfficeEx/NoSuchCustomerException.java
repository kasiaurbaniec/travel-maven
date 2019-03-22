package TravelOfficeEx;

public class NoSuchCustomerException extends Exception {
    public NoSuchCustomerException(){}
    public NoSuchCustomerException(String message){
        super(message);
    }
}
