package ua.rd.pizzaservice.domain;

/**
 * @author Serhii_Mykhliuk
 */
public class Address {
    private String Address;

    public Address(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    @Override
    public String toString() {
        return "Address{" + Address + '\'' +
                '}';
    }
}
