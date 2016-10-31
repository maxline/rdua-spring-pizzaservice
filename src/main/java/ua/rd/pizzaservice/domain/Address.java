package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Serhii_Mykhliuk
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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

    @Override
    //todo хотя equals сравнивает без id, все равно при сохранении нового кастомера, каждый раз в базу пишется новый адрес, даже если такой существует
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return Address.equals(address.Address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Address.hashCode();
        return result;
    }
}
