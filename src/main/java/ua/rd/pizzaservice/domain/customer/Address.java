package ua.rd.pizzaservice.domain.customer;

import javax.persistence.*;

/**
 * @author Serhii_Mykhliuk
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "address", nullable = false)
    private String Address;

    public Address() {
    }

    public Address(String address) {
        Address = address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" + Address + '\'' +
                '}';
    }


    //todo хотя equals сравнивает без id, все равно при сохранении нового кастомера, каждый раз в базу пишется новый адрес, даже если такой существует
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return Address.equals(address.Address);
    }

    @Override
    public int hashCode() {
        return Address.hashCode();
    }
}
