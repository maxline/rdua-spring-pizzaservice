package ua.rd.pizzaservice.domain.customer;

//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;


import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerCard customerCard;

    public Customer() {
    }

    //todo проверить где создается customerCard, контейнер?
    public Customer(String name, Address address, CustomerCard customerCard) {
        this.name = name;
        this.address = address;
        this.customerCard = customerCard;
    }

//    public void increaseBalance(BigDecimal sum) {
//        customerCard.increaseBalance(sum);
//    }
//
//    public BigDecimal getBalance() {
//        return cardBalance;
//    }

    public String getName() {
        return name;
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public Address getAddress() {
        return address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        return customerCard != null ? customerCard.equals(customer.customerCard) : customer.customerCard == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (customerCard != null ? customerCard.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" + name + '\'' +
                ", address='" + address + '\'' +
                ", card balance ='" + customerCard.getBalance() + '\'' +
                '}';
    }
}
