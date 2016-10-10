package ua.rd.pizzaservice.domain;

/**
 * @author andrii
 */
public class Customer {
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
